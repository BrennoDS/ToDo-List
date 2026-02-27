package com.BrennoDs.ToDoList.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean realizado;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data;


    @PrePersist
    public void prePersist(){
        if(this.data == null){
            this.data = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Boolean getRealizado() {
        return realizado;
    }
    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }

     
    
}
