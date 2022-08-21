package com.halligan.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String message;
    private Boolean status;

    protected Todo(){}

    public Todo(String message, Boolean status) {
        this.message = message;
        this.status = status;
        
    }
    @Override
    public String toString() {
      return String.format(
          "Todo[id=%d, message='%s', status='%s']",
          id, message, status);
    }
  
    public Long getId() {
      return id;
    }
  
    public String getMessage() {
      return message;
    }
  
    public Boolean getStatus() {
      return status;
    }
}
