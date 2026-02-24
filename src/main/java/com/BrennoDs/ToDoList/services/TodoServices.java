package com.BrennoDs.ToDoList.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.BrennoDs.ToDoList.entity.Todo;
import com.BrennoDs.ToDoList.repository.TodoRepository;

@Service
public class TodoServices {
    private TodoRepository todoRepository;

    public TodoServices(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
    
    public List<Todo> create( Todo todo){
        todoRepository.save(todo);
        return list();

    }
    
    public List<Todo> list(){
        return todoRepository.findAll();
    }

    
    public List<Todo> update( Todo todo){
        todoRepository.save(todo);
        return list();
    }
   
    public List<Todo> delete( Long id){
        todoRepository.deleteById(id);
        return list();
    }
}
