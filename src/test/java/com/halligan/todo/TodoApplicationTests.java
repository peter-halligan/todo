package com.halligan.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
    private TodoRepository todoRepository;
	

    @Test
    public void givenEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        Todo todoEntity = todoRepository
          .save(new Todo("test a", true));
        Optional<Todo> foundEntity = todoRepository
          .findById(todoEntity.getId());
 
        assertNotNull(foundEntity);
        assertEquals(todoEntity.getMessage(), foundEntity.get().getMessage());
    }


		/**
	 * 
	 */
	@Test
    public void givenGenericEntityRepository_Insert3andretrieve() {
		long count = todoRepository.count();
        todoRepository
          .save(new Todo("test", true));
		todoRepository
          .save(new Todo("test2", true));
		todoRepository
          .save(new Todo("test3", true));
		
		Iterable<Todo> todos = todoRepository.findAll();
        assertNotNull(todos);

		List<Todo> actualList = new ArrayList<Todo>();
		if (todos != null) {
			actualList = StreamSupport
  				.stream(todos.spliterator(), false)
  				.collect(Collectors.toList());
		};
		int length = actualList.size();
        assertEquals(count + 3, length);
    }

	@Test
    public void givenGenericEntityRepository_whenUpdateItemMessageHasChanged_thenOK() {
        Todo todoEntity = todoRepository
          .save(new Todo("test update", true));
        Optional<Todo> foundEntity = todoRepository
          .findById(todoEntity.getId());
		
        assertNotNull(foundEntity);

		Todo updateEntity = foundEntity.get();
		updateEntity.setMessage("updated message");

		Todo newEntity = todoRepository.saveAndFlush(updateEntity);

		assertNotNull(newEntity);
		
        assertEquals("updated message", newEntity.getMessage());

    }

}
