package com.halligan.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {
    
    
    private TodoRepository repository;

    @Autowired
    TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    //Create
    public Todo createTodo (Todo todo) {
        log.info("saving todo: {}", todo);
        return repository.save(todo);
    }

    // READ
    public List<Todo> getTodos() {
        log.info("retrieving todos");
        return repository.findAll();
    }

    public List<Todo> findByStatus(Boolean status){
        log.info("retrieving todos by status: {}", status);
        return repository.findAllByCompleted(status);
    }

    public List<Todo> findByMessage(String message){
        log.info("retrieving todos by message: {}", message);
        return repository.findAllByMessage(message);
    }

    public Todo findById(Long id){
        log.info("retrieving todos by ID: {}", id);
        return repository.findById(id).orElse(null);
    }

    //UPDATE
    public Todo updateTodo(Long id, Todo todo) {
        log.info("Updating todo by ID: {}, {}", id, todo.toString());
        return repository.findById(id).map(existing -> {
            existing.setCompleted(todo.getCompleted());
            existing.setMessage(todo.getMessage());
            return repository.save(existing);
        }).orElseGet(() -> {
            return repository.save(todo);
    });
        
    }

    //DELETE
    public String deleteTodo(Long id){
        log.info("Deleting todo by ID: {}", id);
        repository.deleteById(id);
        return "deleted id: " + id;
    }

}
