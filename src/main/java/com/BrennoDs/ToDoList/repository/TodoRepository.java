package com.BrennoDs.ToDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BrennoDs.ToDoList.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long>{
    
}
