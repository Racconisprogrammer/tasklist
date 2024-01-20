package com.example.tasklist.backendspringboot.repository;


import com.example.tasklist.backendspringboot.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {
}
