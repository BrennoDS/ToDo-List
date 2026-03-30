package com.BrennoDs.ToDoList.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.BrennoDs.ToDoList.entity.Todo;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Todo> response = new RestTemplate().getForEntity("http://localhost:8080/todos/findById/{id}", Todo.class, 1);

        Todo response2 = new RestTemplate().getForObject("http://localhost:8080/todos/findById/{id}", Todo.class, 1);

        new RestTemplate().exchange("http://localhost:8080/todos/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Todo>>() {});

        log.info(response);
        log.info(response2);
    }
    
}
