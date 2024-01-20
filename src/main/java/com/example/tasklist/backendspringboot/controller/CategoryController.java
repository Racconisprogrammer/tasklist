package com.example.tasklist.backendspringboot.controller;


import com.example.tasklist.backendspringboot.entity.Category;
import com.example.tasklist.backendspringboot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryRepository categoryRepository;

    @GetMapping("/test")
    public List<Category> test() {
        List<Category> list = categoryRepository.findAll();
        return list;
    }

    @PostMapping("/add")
    public void add(@RequestBody Category category) {
        categoryRepository.save(category);


    }

}
