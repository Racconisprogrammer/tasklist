package com.example.tasklist.backendspringboot.controller;


import com.example.tasklist.backendspringboot.entity.Task;
import com.example.tasklist.backendspringboot.repository.TaskRepository;
import com.example.tasklist.backendspringboot.search.TaskSearchValue;
import com.example.tasklist.backendspringboot.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private final TaskService taskService;

    @GetMapping("/all")
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(taskService.add(task));
    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        if (task.getId() == null || task.getId() == 0) {
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskService.update(task));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        Task task = null;
        try {
            task = taskService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/delete/id/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            taskService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValue taskSearchValue) {
        String text = taskSearchValue.getTitle() != null ? taskSearchValue.getTitle() : null;

        Integer completed = taskSearchValue.getCompleted() != null ?  taskSearchValue.getCompleted() : null;
        Long priorityId = taskSearchValue.getPriorityId() != null ? taskSearchValue.getPriorityId() : null;
        Long categoryId = taskSearchValue.getCategoryId() != null ? taskSearchValue.getCategoryId() : null;
        String sortColumn = taskSearchValue.getSortColumn() != null ? taskSearchValue.getSortColumn() : null;
        String sortDirection = taskSearchValue.getSortDirection() != null ? taskSearchValue.getSortDirection() : null;
        Integer pageNumber = taskSearchValue.getPageNumber() != null ? taskSearchValue.getPageNumber() : null;
        Integer pageSize = taskSearchValue.getPageSize() != null ? taskSearchValue.getPageSize() : null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortColumn);

        PageRequest pageRequest = PageRequest.of(pageNumber,
                                                 pageSize,
                                                 sort);
        Page result = taskService.findByParams(text, completed, priorityId, categoryId, pageRequest);

        return ResponseEntity.ok(result);
    }
}
