package com.springboot.crud.controller;

import com.springboot.crud.model.Categoria;
import com.springboot.crud.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;
    
    private void addUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("nombre", auth.getName());
        model.addAttribute("isAdmin", auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }
    
    @GetMapping
    public String listCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        addUserInfo(model);
        return "categorias/list";
    }
    
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        addUserInfo(model);
        return "categorias/form";
    }
    
    @PostMapping("/save")
    public String saveCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.saveCategoria(categoria);
        return "redirect:/categorias";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        model.addAttribute("categoria", categoria);
        addUserInfo(model);
        return "categorias/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return "redirect:/categorias";
    }
}
