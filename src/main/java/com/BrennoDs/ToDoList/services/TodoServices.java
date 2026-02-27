package com.BrennoDs.ToDoList.services;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
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


    public Todo findByNome(String nome){
        return todoRepository.findByNome(nome).orElseThrow(
            () -> new RuntimeException("Nome não encontrado")
        );
    }
    

    

    public Todo updateById(@NonNull Long id, @NonNull Todo todo){
        Todo tdAntigo = todoRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Id não encontrado")
        );
        Todo todoAtualizado = Todo.builder()
        .id(tdAntigo.getId())
        .nome(todo.getNome() != null ? todo.getNome() : tdAntigo.getNome())
        .descricao(todo.getDescricao() != null ? todo.getDescricao() : tdAntigo.getDescricao())
        .realizado(todo.getRealizado() != null ? todo.getRealizado() : tdAntigo.getRealizado())
        .data(todo.getData() != null ? todo.getData() : tdAntigo.getData())
        .build();
        return todoRepository.saveAndFlush(todoAtualizado);
    }
   
    public List<Todo> delete( Long id){
        todoRepository.deleteById(id);
        return list();
    }
}
