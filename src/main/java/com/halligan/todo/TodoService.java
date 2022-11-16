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

    TodoConverter todoConverter;


    @Autowired
    TodoService(TodoRepository repository, TodoConverter todoConverter) {
        this.repository = repository;
        this.todoConverter = todoConverter;
    }

    //Create
    public TodoDTO createTodo (TodoDTO todoDTO) {
        log.info("saving todo: {}", todoDTO);
        Todo todo = todoConverter.convertDtoToEntity(todoDTO);
        return todoConverter.convertEntityToDto(repository.save(todo)) ;
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
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        Todo todo = todoConverter.convertDtoToEntity(todoDTO);
        log.info("Updating todo by ID: {}, {}", id, todo.toString());
        return repository.findById(id).map(existing -> {
            existing.setCompleted(todo.getCompleted());
            existing.setMessage(todo.getMessage());
            return todoConverter.convertEntityToDto(repository.save(existing));
        }).orElseGet(() -> {
            return todoConverter.convertEntityToDto(repository.save(todo));
    });
        
    }

    //DELETE
    public void deleteTodo(Long id){
        log.info("Deleting todo by ID: {}", id);
        repository.deleteById(id);
        
    }

}
