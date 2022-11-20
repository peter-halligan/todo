package com.halligan.todo;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;

public class TodoServiceImplIntegrationTest {

    @TestConfiguration
    static class TodoServiceImplIntegrationTesttContextConfiguration {
 
        @Bean
        public TodoService todoService() {
            return new TodoService();
        }
    }

    @Autowired
    private TodoService todoService;
    

    @MockBean
    private TodoRepository todoRepository;

    @BeforeAll
    public void setUp() {
        Todo todo = new Todo("test message", false );
        todo.setId(1L);
        Mockito.when(todoRepository.findById(todo.getId())).thenReturn(Optional.ofNullable(todo));
    }

    // @Test
    // public 
}
