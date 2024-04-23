package com.spring.todorestapi.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {
    @Autowired
    private TodoService todoService;

    @GetMapping("users/{username}/todos")
    public List<Todo> retreiveTodos(@PathVariable String username){
        return todoService.findByUser(username);
    }

    @GetMapping("users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable int id){
        return todoService.findById(id);
    }

    @DeleteMapping("users/{username}/todos/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable int id){
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @PathVariable String username, @RequestBody Todo todo){
        todoService.updateTodo(todo);
        return todo;
    }
    @PostMapping("users/{username}/todos")
    public Todo addTodo(@PathVariable String username, @RequestBody Todo todo){
        Todo createdTodo = todoService.addTodo(username,todo.getDescription(),todo.getTargetDate(),todo.isDone());
        return createdTodo;
    }
}
