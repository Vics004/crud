package com.springboot.crud.service;

import com.springboot.crud.model.Categoria;
import com.springboot.crud.model.Producto;
import com.springboot.crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaService categoriaService;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto saveProducto(Producto producto) {
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            Categoria categoria = categoriaService.getCategoriaById(producto.getCategoria().getId());
            producto.setCategoria(categoria);
        }
        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
