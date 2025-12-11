package com.timely.todo.controller;

import com.timely.todo.dto.TodoRequest;
import com.timely.todo.dto.TodoResponse;
import com.timely.todo.dto.UpdateCompletionRequest;
import com.timely.todo.model.Todo;
import com.timely.todo.service.TodoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping
	public List<TodoResponse> list() {
		return todoService.findAll().stream()
				.map(TodoResponse::fromEntity)
				.toList();
	}

	@GetMapping("/{id}")
	public TodoResponse get(@PathVariable Long id) {
		Todo todo = todoService.findById(id);
		return TodoResponse.fromEntity(todo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TodoResponse create(@Valid @RequestBody TodoRequest request) {
		Todo created = todoService.create(request);
		return TodoResponse.fromEntity(created);
	}

	@PutMapping("/{id}")
	public TodoResponse update(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
		Todo updated = todoService.update(id, request);
		return TodoResponse.fromEntity(updated);
	}

	@PutMapping("/{id}/completion")
	public TodoResponse updateCompletion(@PathVariable Long id, @Valid @RequestBody UpdateCompletionRequest request) {
		Todo updated = todoService.updateCompletion(id, request.completed());
		return TodoResponse.fromEntity(updated);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		todoService.delete(id);
	}
}
