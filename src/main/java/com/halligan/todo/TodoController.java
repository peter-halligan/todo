package com.halligan.todo;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        log.info("getting todos");
        return service.getTodos();
    }

    @PostMapping("/todos")
    public TodoDTO addTodo(@RequestBody TodoDTO todoDTO) {
        log.info("Creating todo: {}", todoDTO);
        return service.createTodo(todoDTO);
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
    public TodoDTO updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO) throws Exception{
        return service.updateTodo(id, todoDTO);
       

    }

    
    
}
