package com.sang.cadastroaplicacao.controllers;


import java.time.Instant;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sang.cadastroaplicacao.models.ToItem;
import com.sang.cadastroaplicacao.repositories.ToItemRepository;

import jakarta.validation.Valid;

@Controller
public class ToItemController {
    private final Logger logger = LoggerFactory.getLogger(ToItemController.class);

    @Autowired 
    private ToItemRepository todaItemRepository;


    @GetMapping("/")
    public ModelAndView index() {
        logger.info("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("todoItems" , todaItemRepository.findAll());
        modelAndView.addObject("hoje", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
      
        return modelAndView;
    }


    @PostMapping("/todo")
    public String createTodoItem(@Valid ToItem toItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-todo-item";
        }

        toItem.setCreatedDate(Instant.now());
        toItem.setModifiedDate(Instant.now());
        todaItemRepository.save(toItem);
        return "redirect:/";
    }

     

    @PostMapping("/todo/{id}")
    public String updateToItem(@PathVariable("id") long id, @Valid ToItem toItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            toItem.setId(id);
            return "update-todo-item";
        }

        toItem.setModifiedDate(Instant.now());
        todaItemRepository.save(toItem);
        return "redirect:/";
    }
}
        
