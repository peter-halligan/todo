package com.halligan.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TodoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired TestRestTemplate restTemplate;

    @Test
    void givenANewTodo_postTodoEndpoint_ShouldAddANewTodo() {
        String baseUrl = "http://localhost:" + port + "/todos";
        Todo todo = new Todo("TestTask", false);
        ResponseEntity<Todo> response = restTemplate.postForEntity(baseUrl, todo, Todo.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Todo newTodo = response.getBody();
        assertThat(newTodo).isNotNull();
        assertEquals(todo.getMessage(), newTodo.getMessage());

    }


    @Test
    void givenTodos_GetTodosEndpoint_ShouldReturnTodoList() {
        String baseUrl = "http://localhost:" + port + "/todos";
        ResponseEntity<Todo[]> response = restTemplate.getForEntity(baseUrl, Todo[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length).isGreaterThanOrEqualTo(2);
    }

    @Test
    void giveAnId_GetTodoEndpoint_ShouldReturnATodo() {
        String baseUrl = "http://localhost:" + port + "/todos/{id}";
        ResponseEntity<Todo> response = restTemplate.getForEntity(baseUrl, Todo.class, 4);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody());
        assertEquals(4,response.getBody().getId());

    }

    @Test
    void givenAnId_PutEndpoint_existingEntity_update_Added() {
        
        Todo todo = new Todo("TestTask", false);
        String baseUrl = "http://localhost:" + port + "/todos";
        ResponseEntity<Todo> response = restTemplate.postForEntity(baseUrl, todo, Todo.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        
        Todo update_todo = new Todo(response.getBody().getMessage(), response.getBody().getCompleted());
        update_todo.setId(response.getBody().getId());

        HttpEntity<Todo> requestEntity = new HttpEntity<>(update_todo);
        String update_baseUrl = "http://localhost:" + port + "/todos/" + update_todo.getId();
        ResponseEntity<Todo> updated_response = restTemplate.exchange(update_baseUrl, HttpMethod.PUT, requestEntity, Todo.class);

        assertThat(updated_response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        assertThat(updated_response).isNotNull();
        assertEquals(response.getBody().getId(), updated_response.getBody().getId());
    }

    @Test
    void givenAnId_DeleteEndpoint_TodoIsDeleted(){
        String baseUrl = "http://localhost:" + port + "/todos";
        ResponseEntity<Todo[]> response = restTemplate.getForEntity(baseUrl, Todo[].class);
        Todo[] todos = response.getBody();
        int count = todos.length;
        Long id = todos[0].getId();

        ResponseEntity<Void> resp = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());

        ResponseEntity<Todo[]> after_response = restTemplate.getForEntity(baseUrl, Todo[].class);
        Todo[] after_todos = after_response.getBody();

        assertEquals(count - 1, after_todos.length);
           
    }


}
