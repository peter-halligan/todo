package com.halligan.todo;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@CrossOrigin(origins = { "http://localhost:3000"})
@RestController(value = "/todos")
public class TodoController {

    TodoService service;

    TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        log.info("getting todos");
        return service.getTodos();
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo) {
        log.info("Creating todo: {}", todo);
        return service.createTodo(todo);
    }

    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        log.info("getting todo by id {}", id);
        return service.findById(id);
    }

    @DeleteMapping("/todos/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id) {
        log.info("delete todo by id {}", id);
        service.deleteTodo(id);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) throws Exception{
        return service.updateTodo(id, todo);
       

    }

    
    
}
