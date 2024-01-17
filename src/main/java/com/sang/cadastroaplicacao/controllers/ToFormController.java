package com.sang.cadastroaplicacao.controllers;
import com.sang.cadastroaplicacao.models.ToItem;
import com.sang.cadastroaplicacao.repositories.ToItemRepository;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
public class ToFormController {
    private final Logger logger = LoggerFactory.getLogger(ToFormController.class);

    @Autowired
    private ToItemRepository todaItemRepository;

    @GetMapping("/create-todo")
    public String showCreateForm(ToItem toItem){
        return "add-todo-item";
    }
    

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        ToItem toItem = todaItemRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Item id: " + id + " nao encontrado"));
    
       model.addAttribute("todo", toItem);
        return "update-todo-item";
    }


    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model) {
        ToItem toItem = todaItemRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Item id: " + id + " produto nao encontrado"));
    
        todaItemRepository.delete(toItem);
        return "redirect:/";
    }
}

