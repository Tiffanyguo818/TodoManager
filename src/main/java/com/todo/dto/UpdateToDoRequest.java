package com.todo.dto;

import com.todo.model.ToDoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdateToDoRequest {
    @NotNull @Min(1)
    private Long id;

    @NotBlank(message="userId is mandatory")
    private String userId;

    private String name;
    private String description;
    private ToDoStatus status;
    private Date dueDate;

}
