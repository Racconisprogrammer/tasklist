package com.example.tasklist.backendspringboot.controller;


import com.example.tasklist.backendspringboot.entity.Priority;
import com.example.tasklist.backendspringboot.repository.PriorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/priority")
@RequiredArgsConstructor
public class PriorityController {

    @Autowired
    private final PriorityRepository priorityRepository;

    @GetMapping("/test")
    public List<Priority> test() {
        List<Priority> list = priorityRepository.findAll();
        System.out.println("list " + list);
        return list;
    }
}
