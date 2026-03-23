package com.BrennoDs.ToDoList.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BrennoDs.ToDoList.Request.ToDoGetRequest;
import com.BrennoDs.ToDoList.Request.ToDoPostRequestBody;
import com.BrennoDs.ToDoList.Request.ToDoPutRequestBody;
import com.BrennoDs.ToDoList.entity.Todo;
import com.BrennoDs.ToDoList.services.TodoServices;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoServices todoServices;

    public TodoController(TodoServices todoServices) {
        this.todoServices = todoServices;
    }

    @GetMapping
    public List<Todo> list(){
        return todoServices.list();
    }

    @GetMapping("/search")
    public Todo getByNome(@RequestBody ToDoGetRequest nome) {
        return todoServices.findByNome(nome);
    }

    @PostMapping
    public List<Todo> create(@RequestBody ToDoPostRequestBody toDoPostRequestBody){
        return todoServices.create(toDoPostRequestBody);
    }
    
    @PutMapping("{id}")
    public Todo updateById(@PathVariable Long id,@RequestBody ToDoPutRequestBody toDoPutRequestBody){
        return todoServices.updateById(id, toDoPutRequestBody);
    }
    
    @DeleteMapping("{id}")
    public List<Todo> delete(@PathVariable("id") Long id){
        return todoServices.delete(id);
    }
}
