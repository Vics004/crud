package com.springboot.crud.controller;

import com.springboot.crud.model.Producto;
import com.springboot.crud.service.CategoriaService;
import com.springboot.crud.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    private void addUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("nombre", auth.getName());
        model.addAttribute("isAdmin", auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }
    
    @GetMapping
    public String listProductos(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        addUserInfo(model);
        return "productos/list";
    }
    
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        addUserInfo(model);
        return "productos/form";
    }
    
    @PostMapping("/save")
    public String saveProducto(@ModelAttribute("producto") Producto producto) {
        productoService.saveProducto(producto);
        return "redirect:/productos?success";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductoById(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        addUserInfo(model);
        return "productos/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return "redirect:/productos?deleted";
    }
}