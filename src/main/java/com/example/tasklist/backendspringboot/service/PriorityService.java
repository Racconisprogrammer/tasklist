package com.example.tasklist.backendspringboot.service;

import com.example.tasklist.backendspringboot.entity.Priority;
import com.example.tasklist.backendspringboot.repository.PriorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PriorityService {

    @Autowired
    private final PriorityRepository repository;

    public List<Priority> findAll() {
        return repository.findAllByOrderByTitleAsc();
    }

    public Priority add(Priority priority) {
        return repository.save(priority);
    }

    public Priority update(Priority priority){
        return repository.save(priority);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Priority findById(Long id){
        return repository.findById(id).get();
    }

    public List<Priority> findByTitle(String text){
        return repository.findByTitle(text);
    }

}
