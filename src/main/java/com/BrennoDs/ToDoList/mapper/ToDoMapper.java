package com.BrennoDs.ToDoList.mapper;

import org.mapstruct.Mapper;

import com.BrennoDs.ToDoList.Request.ToDoGetRequest;
import com.BrennoDs.ToDoList.Request.ToDoPostRequestBody;
import com.BrennoDs.ToDoList.Request.ToDoPutRequestBody;
import com.BrennoDs.ToDoList.entity.Todo;

@Mapper(componentModel = "spring")
public interface ToDoMapper {

    public abstract Todo mapperToDo(ToDoPostRequestBody toDoPostRequestBody);

    public abstract Todo mapperToDo(ToDoPutRequestBody toDoPutRequestBody);

    public abstract Todo mapperToDo(ToDoGetRequest toDoGetRequestBody);
}
