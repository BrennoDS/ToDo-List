package com.BrennoDs.ToDoList.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BrennoDs.ToDoList.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long>{
    
    public Optional<Todo> findByNome(String nome);

}
