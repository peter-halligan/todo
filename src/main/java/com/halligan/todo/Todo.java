package com.halligan.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
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
  
    public Long getId() {
      return id;
    }
  
    public String getMessage() {
      return message;
    }
  
    public Boolean getCompleted() {
      return completed;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCompleted(Boolean completed) {
      this.completed = completed;
    }

    public void setId(int id) {
    }
}
