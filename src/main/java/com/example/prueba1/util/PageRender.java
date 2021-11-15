package com.example.prueba1.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
    private String url;
    private Page<T> page;
    private int totalPaginas;
    private int itemsPaginas;
    private int pagActual;
    private List<PageItem> paginas;

    public PageRender() {
    }
    
    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<PageItem>();

        itemsPaginas = page.getSize();
        totalPaginas = page.getTotalPages();
        pagActual = page.getNumber();

        int desde, hasta;

        if (totalPaginas <= itemsPaginas) {
            desde = 1;
            hasta = totalPaginas;
        } else {
            if (pagActual <= itemsPaginas/2) {
                desde = 1;
                hasta = itemsPaginas;
            } else if (pagActual >= totalPaginas - itemsPaginas/2) {
                desde = totalPaginas - itemsPaginas + 1;
                hasta = itemsPaginas;
            } else {
                desde = pagActual - itemsPaginas/2;
                hasta = itemsPaginas;
            }
        }

        for (int i = 0; i < hasta; i++) {
            paginas.add(new PageItem(desde+i, pagActual == desde + i));
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public int getItemsPaginas() {
        return itemsPaginas;
    }

    public int getPagActual() {
        return pagActual;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}