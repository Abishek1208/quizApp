package com.spring.todorestapi.todo;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int count = 0;
    static {
        todos.add(new Todo(++count,"abishek","Learn Spring1", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++count,"abishek","Learn react1", LocalDate.now().plusYears(2),false));
        todos.add(new Todo(++count,"abishek","Learn java1", LocalDate.now().plusYears(3),false));
    }

    public List<Todo> findByUser(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public Todo addTodo(String username, String desc, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++count,username,desc,targetDate,done);
        todos.add(todo);
        return todo;
    }
    public void deleteTodo(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }
    public void updateTodo(Todo todo){
        deleteTodo(todo.getId());
        todos.add(todo);
    }
}
