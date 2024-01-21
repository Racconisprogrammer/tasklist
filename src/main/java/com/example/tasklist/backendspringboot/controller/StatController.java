package com.example.tasklist.backendspringboot.controller;


import com.example.tasklist.backendspringboot.entity.Stat;
import com.example.tasklist.backendspringboot.repository.StatRepository;
import com.example.tasklist.backendspringboot.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stat")
public class StatController {

    @Autowired
    private final StatService statService;

    @GetMapping("/all")
    public List<Stat> findAll() {
        return statService.findAll();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Stat> findById(@PathVariable Long id) {
        return ResponseEntity.ok(statService.findById(id));
    }
}
