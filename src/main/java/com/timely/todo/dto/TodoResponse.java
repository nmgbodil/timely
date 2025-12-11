package com.timely.todo.dto;

import com.timely.todo.model.Todo;
import java.time.LocalDateTime;

public record TodoResponse(
		Long id,
		String title,
		String description,
		boolean completed,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
) {

	public static TodoResponse fromEntity(Todo todo) {
		return new TodoResponse(
				todo.getId(),
				todo.getTitle(),
				todo.getDescription(),
				todo.isCompleted(),
				todo.getCreatedAt(),
				todo.getUpdatedAt()
		);
	}
}
