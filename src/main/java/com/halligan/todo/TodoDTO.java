package com.halligan.todo;


import lombok.Data;

@Data
public class TodoDTO {
    
    private Long id;

    private String message;
    private Boolean completed;

  
}