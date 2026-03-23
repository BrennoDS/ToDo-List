package com.BrennoDs.ToDoList.entity;

import java.time.LocalDateTime;


import com.BrennoDs.ToDoList.Enums.ToDoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

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

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String descricao;
    private ToDoStatus status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataFinalizacao;



    @PrePersist
    public void prePersist(){
        if(this.dataCriacao == null){
            this.dataCriacao = LocalDateTime.now();
        }
        if(this.dataFinalizacao == null || this.dataFinalizacao.isBefore(this.dataCriacao)){
            this.dataFinalizacao = this.dataCriacao.plusDays(1);
        }
        
        if(this.status == null){
            this.status = ToDoStatus.PENDENTE;
        }
        if(this.descricao == null){
            this.descricao = "";
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
    public ToDoStatus getStatus() {
        return status;
    }
    public void setStatus(ToDoStatus status) {
        this.status = status;
    }
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }
    

}
