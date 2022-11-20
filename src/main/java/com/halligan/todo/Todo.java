package com.halligan.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Entity
@Getter
@Setter
public class Todo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String message;
    private Boolean completed;


    public Todo(String message, Boolean completed) {
        this.message = message;
        this.completed = completed;
        
    }
    @Override
    public String toString() {
      return String.format(
          "Todo[id=%d, message='%s', status='%s']",
          id, message, completed);
    }
  
}
