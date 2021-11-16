package com.example.prueba1.controller;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.prueba1.editors.AutorEditor;
import com.example.prueba1.editors.CategoriaEditor;
import com.example.prueba1.models.domain.Autor;
import com.example.prueba1.models.domain.Categoria;
import com.example.prueba1.models.domain.Libro;
import com.example.prueba1.services.AutorService;
import com.example.prueba1.services.CategoriaService;
import com.example.prueba1.services.LibroService;
import com.example.prueba1.util.PageRender;

@Controller
@SessionAttributes("libro")
public class LibroController {
    @Autowired
    private LibroService libroService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private AutorService autorService;

    @Autowired
    private AutorEditor autorEditor;

    @Autowired
    private CategoriaEditor categoriaEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Autor.class, "autor", autorEditor);
        binder.registerCustomEditor(Categoria.class, "categoria", categoriaEditor);
    }

    @RequestMapping(value = "indexLibro", method = RequestMethod.GET)
    public String indexLibro(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pager = PageRequest.of(page, 5);

        Page<Libro> libros = libroService.findAll(pager);
        PageRender<Libro> pageRender = new PageRender<>("/index", libros);

        model.addAttribute("titulo", "Listado de libros");
        model.addAttribute("libros", libros);
        model.addAttribute("page", pageRender);

        System.out.println(pageRender);

        return "contenido/contLibro";
    }

    @GetMapping("/crearLibro")
    public String crearLibro(Model model) {
        Libro libro = new Libro();
        model.addAttribute("libro", libro);
        return "crear/crearLibro";
    }

    @PostMapping("/formLibro")
    public String formLibro(@Valid Libro libro, BindingResult result, Model model, SessionStatus status,
            @RequestParam("file") MultipartFile imagen) {
        if (result.hasErrors()) {
            return "crear/crearLibro";
        }

        if (!imagen.isEmpty()) {
			String extension = "";
			int index = imagen.getOriginalFilename().lastIndexOf('.');

			if (index > 0) {
				extension = imagen.getOriginalFilename().substring(index + 1);
			}

			String[] extensiones = { "png", "jpg", "jpeg" };
			boolean contieneExtension = Arrays.stream(extensiones).anyMatch(extension::equals);

			if (!contieneExtension) {
				model.addAttribute("extension", "La extension no corresponde a una Imagen");
				return "crear/crearLibro";
			}

			String uniqueFileName = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
			Path rootPath = Paths.get("uploads/libro").resolve(uniqueFileName);
			Path rootAbsolutePath = rootPath.toAbsolutePath();

			try {
				Files.copy(imagen.getInputStream(), rootAbsolutePath);
				libro.setImagen(uniqueFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

        libroService.save(libro);

        return "redirect:indexLibro";
    }

    @GetMapping(value = "/uploads/libro/{filename:.+}")
	public ResponseEntity<Resource> getImagenAutor(@PathVariable String filename) {
		Path pathImagen = Paths.get("uploads/libro").resolve(filename).toAbsolutePath();

		Resource resource = null;

		try {
			resource = new UrlResource(pathImagen.toUri());
			if (!resource.exists() && !resource.isReadable()) {
				throw new RuntimeException("Error:  La imagen no se pudo cargar" + pathImagen.getFileName());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

    @GetMapping(value = "/modificarLibro/{id}")
    public String modificarLibro(@PathVariable(value = "id") Long id, Map<String, Object> model,
            RedirectAttributes flash) {

        Libro libro = null;

        if (id > 0) {
            libro = libroService.findOne(id);

            if (libro == null) {
                flash.addFlashAttribute("Error", "Libro a buscar no existe");
            }
        } else {
            flash.addFlashAttribute("Error", "El id no puede ser cero!");
        }

        model.put("titulo", "Editar Libro");
        model.put("libro", libro);

        return "modificar/modificarLibro";
    }

    /*
     * @PostMapping("/modLibro") public String mobLibro(@Valid Libro libro,
     * BindingResult result, Model model, SessionStatus status) { if
     * (result.hasErrors()) { return "modificar/modificarLibro"; }
     * libroService.modificarLibro(libro);
     * 
     * return "listas/listaLibro"; }
     */

    @GetMapping("/eliminarLibro/{id}")
    public String eliminarLibro(@SessionAttribute(name = "libro", required = false) Libro libro,
            @PathVariable Integer id, Model model) {
        libroService.delete(libro);
        setValues(0, model);
        return "contenido/contLibro";
    }

    public void setValues(int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Libro> libros = libroService.findAll(pageRequest);
        PageRender<Libro> pageRender = new PageRender<>("/indexLibro", libros);
        model.addAttribute("libros", libros);
        model.addAttribute("page", pageRender);
    }

    @GetMapping("/listaLibro")
    public String listaLibros(Model model) {
        return "listas/listaLibro";
    }

    @ModelAttribute("libros")
    public List<Libro> libros() {
        return libroService.findAll();
    }

    @ModelAttribute("autores")
    public List<Autor> autores() {
        return autorService.findAll();
    }

    @ModelAttribute("categorias")
    public List<Categoria> categorias() {
        return categoriaService.findAll();
    }

}