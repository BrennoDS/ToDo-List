package com.BrennoDs.ToDoList.services;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.BrennoDs.ToDoList.Request.ToDoPostRequestBody;
import com.BrennoDs.ToDoList.Request.ToDoPutRequestBody;
import com.BrennoDs.ToDoList.entity.Todo;
import com.BrennoDs.ToDoList.exception.BadRequestException;
import com.BrennoDs.ToDoList.mapper.ToDoMapper;
import com.BrennoDs.ToDoList.repository.TodoRepository;

@Service
public class TodoServices {
    private TodoRepository todoRepository;
    private ToDoMapper toDoMapper;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    public TodoServices(TodoRepository todoRepository, ToDoMapper toDoMapper){
        this.todoRepository = todoRepository;
        this.toDoMapper = toDoMapper;

    }

    public Todo findById(Long id){
        return todoRepository.findById(id).orElseThrow(
            () -> new BadRequestException("Id não encontrado")
        );
    }
    
    @Transactional(rollbackFor = Exception.class)
    public List<Todo> create(@NonNull ToDoPostRequestBody toDoPostRequestBody){
        todoRepository.save(toDoMapper.mapperToDo(toDoPostRequestBody));
        return list();

    }
    
    public Page<Todo> listAll(Pageable pageable){
        return todoRepository.findAll(pageable);
    }
    public List<Todo> list(){
        return todoRepository.findAll();
    }


    public Todo findByNome(String nome){
        return todoRepository.findByNome(nome).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome não encontrado")
        );
    }
    

    

    public Todo updateById(@NonNull Long id, @NonNull ToDoPutRequestBody toDoPutRequestBody){
        Todo tdAntigo = todoRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado")
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
