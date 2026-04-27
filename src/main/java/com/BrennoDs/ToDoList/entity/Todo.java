package com.BrennoDs.ToDoList.entity;

import java.time.LocalDateTime;


import com.BrennoDs.ToDoList.Enums.ToDoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
@ToString
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private ToDoStatus status;

    @Column(updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

  
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataLimite;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataConclusao;



    @PrePersist
    public void prePersist(){
        this.dataCriacao = LocalDateTime.now();
        if(this.dataLimite == null || this.dataLimite.isBefore(dataCriacao)){
            this.dataLimite = this.dataCriacao.plusDays(1);
        }
        if(this.status == null){
            this.status = ToDoStatus.PENDENTE;
        }
        if(this.descricao == null){
            this.descricao = "";
        }
    }
    

}
