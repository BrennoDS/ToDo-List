package com.BrennoDs.ToDoList.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.BrennoDs.ToDoList.Enums.ToDoStatus;
import com.BrennoDs.ToDoList.Request.ToDoPostRequestBody;
import com.BrennoDs.ToDoList.Request.ToDoPutRequestBody;
import com.BrennoDs.ToDoList.entity.Todo;
import com.BrennoDs.ToDoList.exception.BadRequestException;
import com.BrennoDs.ToDoList.mapper.ToDoMapper;
import com.BrennoDs.ToDoList.repository.TodoRepository;

import jakarta.validation.Valid;

@Service
public class TodoServices {
    private final TodoRepository todoRepository;
    private final ToDoMapper toDoMapper;

    public TodoServices(TodoRepository todoRepository, ToDoMapper toDoMapper){
        this.todoRepository = todoRepository;
        this.toDoMapper = toDoMapper;

    }

    public Todo findById(Long id){
        return todoRepository.findById(id).orElseThrow(
            () -> new BadRequestException("Id não encontrado")
        );
    }
    
    public Todo findByNome(String nome){
        return todoRepository.findByNome(nome).orElseThrow(
            () -> new BadRequestException("Nome não encontrado")
        );
    }

    public Page<Todo> listAll(Pageable pageable){
        return todoRepository.findAll(pageable);
    }

    public List<Todo> listAll(){
        return todoRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Todo> create(@NonNull ToDoPostRequestBody toDoPostRequestBody){
        todoRepository.save(toDoMapper.mapperToDo(toDoPostRequestBody));
        return listAll();

    }
    
    public Todo updateById(@NonNull Long id, ToDoPutRequestBody toDoPutRequestBody){
        Todo tdAntigo = findById(id);
        
        Todo todoAtualizado = Todo.builder()
        .id(tdAntigo.getId())
        .nome(toDoPutRequestBody.getNome() != null ? toDoPutRequestBody.getNome() : tdAntigo.getNome())
        .descricao(toDoPutRequestBody.getDescricao() != null ? toDoPutRequestBody.getDescricao() : tdAntigo.getDescricao())
        .status(toDoPutRequestBody.getStatus() != null ? toDoPutRequestBody.getStatus() : tdAntigo.getStatus())
        .dataCriacao(tdAntigo.getDataCriacao())
        .dataLimite(toDoPutRequestBody.getDataLimite() != null ? toDoPutRequestBody.getDataLimite() : tdAntigo.getDataLimite())
        .dataConclusao(conclusao(toDoPutRequestBody, tdAntigo))
        .build();

        if(todoAtualizado.getStatus() != ToDoStatus.CONCLUIDO){
            if(todoAtualizado.getDataLimite().isBefore(LocalDateTime.now())){
                todoAtualizado.setStatus(ToDoStatus.ATRASADO);
            }
            else{
                todoAtualizado.setStatus(ToDoStatus.PENDENTE);
            }
        }
        return todoRepository.saveAndFlush(todoAtualizado);


    }

   
    public List<Todo> delete(Long id){
        findById(id);
        todoRepository.deleteById(id);
        return listAll();
    }

    @Scheduled(cron = "*/10 * * * * *")
    @Transactional
    public void atualizarVencidos(){
        List<Todo> vencidos = todoRepository.findByDataLimiteBeforeAndStatusNotIn(
            LocalDateTime.now(),
            List.of(ToDoStatus.CONCLUIDO, ToDoStatus.ATRASADO)
        );
        vencidos.forEach(t -> t.setStatus(ToDoStatus.ATRASADO));
        todoRepository.saveAll(vencidos);
    }

    private LocalDateTime conclusao(ToDoPutRequestBody att, Todo tdAntigo){
        if(att.getStatus() == ToDoStatus.CONCLUIDO && tdAntigo.getDataConclusao() == null){
            return LocalDateTime.now();
        }
        return tdAntigo.getDataConclusao();
    }
}
 