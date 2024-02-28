package com.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todo.model.ToDoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreateToDoRequest {
    @NotBlank(message="userId is mandatory")
    private String userId;

    @NotBlank(message="todo name is mandatory")
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull
    private Date dueDate;
}
