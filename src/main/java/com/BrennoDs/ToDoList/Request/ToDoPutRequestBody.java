package com.BrennoDs.ToDoList.Request;

import java.time.LocalDateTime;
import com.BrennoDs.ToDoList.Enums.ToDoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ToDoPutRequestBody {
    private Long id;
    private String nome;
    private String descricao;
    private ToDoStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataLimite;
}
