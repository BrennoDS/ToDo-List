package com.BrennoDs.ToDoList.Request;

import java.time.LocalDateTime;
import com.BrennoDs.ToDoList.Enums.ToDoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ToDoPostRequestBody {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String descricao;
    private ToDoStatus status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataFinalizacao;
}
