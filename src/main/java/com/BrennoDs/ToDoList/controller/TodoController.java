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
    @PostMapping
    public List<Todo> create(@RequestBody Todo todo){
        return todoServices.create(todo);
    }
    @GetMapping
    public List<Todo> list(){
        return todoServices.list();
    }

    @GetMapping("/search")
    public Todo getByNome(@RequestParam String nome) {
        return todoServices.findByNome(nome);
    }
    

    @PutMapping("{id}")
    public Todo updateById(@PathVariable Long id,@RequestBody Todo todo){
        return todoServices.updateById(id, todo);
    }
    
    @DeleteMapping("{id}")
    public List<Todo> delete(@PathVariable("id") Long id){
        return todoServices.delete(id);
    }
}
