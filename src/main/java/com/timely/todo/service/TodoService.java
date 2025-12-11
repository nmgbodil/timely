package com.timely.todo.service;

import com.timely.todo.dto.TodoRequest;
import com.timely.todo.model.Todo;
import com.timely.todo.repository.TodoRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TodoService {

	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<Todo> findAll() {
		return todoRepository.findAll();
	}

	public Todo findById(Long id) {
		return todoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found"));
	}

	@Transactional
	public Todo create(TodoRequest request) {
		Todo todo = new Todo();
		applyRequest(todo, request);
		return todoRepository.save(todo);
	}

	@Transactional
	public Todo update(Long id, TodoRequest request) {
		Todo todo = findById(id);
		applyRequest(todo, request);
		return todo;
	}

	@Transactional
	public Todo updateCompletion(Long id, boolean completed) {
		Todo todo = findById(id);
		todo.setCompleted(completed);
		return todo;
	}

	public void delete(Long id) {
		if (!todoRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
		}
		todoRepository.deleteById(id);
	}

	private void applyRequest(Todo todo, TodoRequest request) {
		todo.setTitle(request.title());
		todo.setDescription(request.description());
		if (request.completed() != null) {
			todo.setCompleted(request.completed());
		} else if (todo.getId() == null) {
			todo.setCompleted(false);
		}
	}
}
