package com.timely.todo.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateCompletionRequest(
		@NotNull(message = "completed is required")
		Boolean completed
) {
}
