package com.BrennoDs.ToDoList.services;

import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.BrennoDs.ToDoList.Request.ToDoGetRequest;
import com.BrennoDs.ToDoList.Request.ToDoPostRequestBody;
import com.BrennoDs.ToDoList.Request.ToDoPutRequestBody;
import com.BrennoDs.ToDoList.entity.Todo;
import com.BrennoDs.ToDoList.repository.TodoRepository;

@Service
public class TodoServices {
    private TodoRepository todoRepository;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public TodoServices(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
    
    
    public List<Todo> create(@NonNull ToDoPostRequestBody toDoPostRequestBody){
        Todo todo = Todo.builder()
        .nome(toDoPostRequestBody.getNome())
        .descricao(toDoPostRequestBody.getDescricao())
        .status(toDoPostRequestBody.getStatus())
        .dataCriacao(toDoPostRequestBody.getDataCriacao())
        .dataFinalizacao(toDoPostRequestBody.getDataFinalizacao())
        .build();
        todoRepository.save(todo);
        return list();

    }
    
    public List<Todo> list(){
        return todoRepository.findAll();
    }


    public Todo findByNome(ToDoGetRequest toDoGetRequest){
        String nome = toDoGetRequest.getNome();
        return todoRepository.findByNome(nome).orElseThrow(
            () -> new RuntimeException("Nome não encontrado")
        );
    }
    

    

    public Todo updateById(@NonNull Long id, @NonNull ToDoPutRequestBody toDoPutRequestBody){
        Todo tdAntigo = todoRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Id não encontrado")
        );
        Todo todoAtualizado = Todo.builder()
        .id(tdAntigo.getId())
        .nome(toDoPutRequestBody.getNome() != null ? toDoPutRequestBody.getNome() : tdAntigo.getNome())
        .descricao(toDoPutRequestBody.getDescricao() != null ? toDoPutRequestBody.getDescricao() : tdAntigo.getDescricao())
        .status(toDoPutRequestBody.getStatus() != null ? toDoPutRequestBody.getStatus() : tdAntigo.getStatus())
        .dataCriacao(toDoPutRequestBody.getDataCriacao() != null ? toDoPutRequestBody.getDataCriacao() : tdAntigo.getDataCriacao())
        .dataFinalizacao(toDoPutRequestBody.getDataFinalizacao() != null ? toDoPutRequestBody.getDataFinalizacao() : tdAntigo.getDataFinalizacao())
        .build();
        return todoRepository.saveAndFlush(todoAtualizado);
    }

   
    public List<Todo> delete(Long id){
        todoRepository.deleteById(id);
        return list();
    }
}
