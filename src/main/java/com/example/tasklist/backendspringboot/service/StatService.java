package com.example.tasklist.backendspringboot.service;

import com.example.tasklist.backendspringboot.entity.Stat;
import com.example.tasklist.backendspringboot.repository.StatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StatService {

    @Autowired
    private final StatRepository repository;

    public List<Stat> findAll() {
        return repository.findAll();
    }

    public Stat findById(Long id){
        return repository.findById(id).get();
    }


}

