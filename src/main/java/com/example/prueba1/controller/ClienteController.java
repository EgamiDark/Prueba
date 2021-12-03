package com.example.prueba1.controller;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.example.prueba1.models.domain.Cliente;
import com.example.prueba1.services.ClienteService;
import com.example.prueba1.util.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "indexCliente", method = RequestMethod.GET)
	public String indexCliente(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		setValues(page, model);
		return "contenido/contCliente";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/crearCliente")
	public String crearCliente(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "crear/crearCliente";
	}

	@PostMapping("/formCliente")
	public String formCliente(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status,
			@RequestParam("file") MultipartFile imagen) {
		if (result.hasErrors()) {
			if (!imagen.isEmpty()) {
				String extension = "";
				int index = imagen.getOriginalFilename().lastIndexOf('.');

				if (index > 0) {
					extension = imagen.getOriginalFilename().substring(index + 1);
				}

				String[] extensiones = {"PNG","JPG","JPEG", "png", "jpg", "jpeg" };
				boolean contieneExtension = Arrays.stream(extensiones).anyMatch(extension::equals);

				if (!contieneExtension) {
					model.addAttribute("extension", "La extension no corresponde a una Imagen");
					return "crear/crearCliente";
				}
			}
			return "crear/crearCliente";
		}

		if (!imagen.isEmpty()) {
			String extension = "";
			int index = imagen.getOriginalFilename().lastIndexOf('.');

			if (index > 0) {
				extension = imagen.getOriginalFilename().substring(index + 1);
			}

			String[] extensiones = {"PNG","JPG","JPEG", "png", "jpg", "jpeg" };
			boolean contieneExtension = Arrays.stream(extensiones).anyMatch(extension::equals);

			if (!contieneExtension) {
				model.addAttribute("extension", "La extension no corresponde a una Imagen");
				return "crear/crearCliente";
			}

			String uniqueFileName = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
			Path rootPath = Paths.get("uploads/cliente").resolve(uniqueFileName);
			Path rootAbsolutePath = rootPath.toAbsolutePath();

			try {
				Files.copy(imagen.getInputStream(), rootAbsolutePath);
				cliente.setImagen(uniqueFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		clienteService.save(cliente);
		setValues(0, model);
		return "contenido/contCliente";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/uploads/cliente/{filename:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String filename) {

		Path pathFoto = Paths.get("uploads/cliente").resolve(filename).toAbsolutePath();

		Resource recurso = null;

		try {
			recurso = new UrlResource(pathFoto.toUri());
			if (!recurso.exists() && !recurso.isReadable()) {
				throw new RuntimeException("Error:  La imagen no se pudo cargar" + pathFoto.getFileName());
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);

	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/modificarCliente/{id}")
	public String modificarCliente(@SessionAttribute(name = "cliente", required = false) Cliente cliente,
			@PathVariable Long id, Model model) {
		cliente = clienteService.findOne(id);
		model.addAttribute("cliente", cliente);
		return "modificar/modificarCliente";
	}

	@PostMapping("/modCliente")
	public String modificarLibro(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status,
			@RequestParam("file") MultipartFile imagen) {
		if (result.hasErrors()) {
			return "modificar/modificarCliente";
		}

		Cliente clienteFromDB = clienteService.findOne(cliente.getId());
		String nombreImagen = clienteFromDB.getImagen().split("_", 2)[1];
		String nombreImagenMultipart = imagen.getOriginalFilename();

		if (!imagen.isEmpty()) {
			if (nombreImagen.equals(nombreImagenMultipart)) {
				cliente.setImagen(clienteFromDB.getImagen());
			} else {

				String extension = "";
				int index = imagen.getOriginalFilename().lastIndexOf('.');

				if (index > 0) {
					extension = imagen.getOriginalFilename().substring(index + 1);
				}

				String[] extensiones = {"PNG","JPG","JPEG", "png", "jpg", "jpeg" };
				boolean contieneExtension = Arrays.stream(extensiones).anyMatch(extension::equals);

				if (!contieneExtension) {
					model.addAttribute("extension", "La extension no corresponde a una Imagen");
					return "modificar/modificarCliente";
				}

				String uniqueFileName = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
				Path rootPath = Paths.get("uploads/cliente").resolve(uniqueFileName);
				Path rootAbsolutePath = rootPath.toAbsolutePath();
				try {
					Files.copy(imagen.getInputStream(), rootAbsolutePath);
					cliente.setImagen(uniqueFileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			// Si no viene una imagen en el multipart
			cliente.setImagen(clienteFromDB.getImagen());
		}

		clienteService.save(cliente);

		return "redirect:indexCliente";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/eliminarCliente/{id}")
	public String eliminarCliente(@SessionAttribute(name = "cliente", required = false) Cliente cliente,
			@PathVariable Long id, Model model) {
		clienteService.delete(id);
		setValues(0, model);
		return "contenido/contCliente";
	}

	public void setValues(int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/indexCliente", clientes);
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
	}

}
