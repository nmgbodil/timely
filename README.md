# timely

Simple Spring Boot todo list with an H2 in-memory database.

## Running the app
- Start the API and UI: `./gradlew bootRun`
- Visit the UI: http://localhost:8080/
- H2 console (read-only for debugging): http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:timelydb`)

## API
- `GET /api/todos` – list todos
- `POST /api/todos` – create (`{ "title": "...", "description": "...", "completed": false }`)
- `GET /api/todos/{id}` – fetch one
- `PUT /api/todos/{id}` – replace
- `PUT /api/todos/{id}/completion` – toggle completion (`{ "completed": true }`)
- `DELETE /api/todos/{id}` – remove
