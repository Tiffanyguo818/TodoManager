package com.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todo.model.ToDoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class QueryToDoRequest {
    @NotBlank(message="userId is mandatory")
    private String userId;

    private ToDoStatus status;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;
}
