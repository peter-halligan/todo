package com.halligan.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TodoDataLoader implements CommandLineRunner { 

	private final TodoRepository repository;

	@Autowired
	public TodoDataLoader(TodoRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.repository.save(new Todo("FirstTask", false));
        this.repository.save(new Todo("SecondTask", true));
	}
}
