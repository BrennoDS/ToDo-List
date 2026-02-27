package com.BrennoDs.ToDoList.Enums;
public enum ToDoStatus {
    PENDENTE(1, "Pendente"),
    CONCLUIDO(2, "Concluído"),
    ATRASADO(3, "Atrasado"),
    EM_ANDAMENTO(4, "Em andamento");

    private final int code;
    private final String description;
    ToDoStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }


    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}


