package com.halligan.todo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TodoConverter {

    public TodoDTO convertEntityToDto(Todo todo) {
        ModelMapper modelMapper = new ModelMapper();
        TodoDTO customerDTO = modelMapper.map(todo, TodoDTO.class);
        return customerDTO;
    }

    public Todo convertDtoToEntity(TodoDTO todoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Todo todo = modelMapper.map(todoDTO, Todo.class);
        return todo;
    }
}