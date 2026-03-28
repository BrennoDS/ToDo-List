package com.BrennoDs.ToDoList.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BrennoDs.ToDoList.Request.ToDoPostRequestBody;
import com.BrennoDs.ToDoList.Request.ToDoPutRequestBody;
import com.BrennoDs.ToDoList.entity.Todo;
import com.BrennoDs.ToDoList.services.TodoServices;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoServices todoServices;

    public TodoController(TodoServices todoServices) {
        this.todoServices = todoServices;
    }

    @GetMapping
    public ResponseEntity<Page<Todo>> list(Pageable pageable){
        return ResponseEntity.ok(todoServices.listAll(pageable));
    }

    @GetMapping("/findById")
    public ResponseEntity<Todo> getById(@RequestParam Long Id){
        return ResponseEntity.ok(todoServices.findById(Id));
    }

    @GetMapping("/findByNome")
    public ResponseEntity<Todo> getByNome(@RequestParam(required = false) String nome) {
        return new ResponseEntity<Todo>(todoServices.findByNome(nome), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Todo>> create(@RequestBody @Valid ToDoPostRequestBody toDoPostRequestBody){
        return new ResponseEntity<List<Todo>>(todoServices.create(toDoPostRequestBody), HttpStatus.CREATED);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Todo> updateById(@PathVariable Long id,@RequestBody @Valid ToDoPutRequestBody toDoPutRequestBody){
        return new ResponseEntity<Todo>(todoServices.updateById(id, toDoPutRequestBody), HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<List<Todo>> delete(@PathVariable("id") Long id){
        return new ResponseEntity<List<Todo>>(todoServices.delete(id), HttpStatus.OK);
    }
}
