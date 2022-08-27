package com.halligan.todo;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TodoService {
    
    TodoRepository repository;

    //Create
    public Todo createTodo (Todo todo) {
        log.info("saving todo: {}", todo);
        return repository.save(todo);
    }

    public List<Todo> getTodos() {
        log.info("retrieving todos");
        return repository.findAll();
    }

    public List<Todo> findByStatus(Boolean status){
        log.info("retrieving todos by status: {}", status);
        return repository.findAllByStatus(status);
    }

    public List<Todo> findByMessage(String message){
        log.info("retrieving todos by message: {}", message);
        return repository.findAllByMessage(message);
    }

    public Todo findById(Long id){
        return repository.findById(id).orElse(null);
    }

}
