package com.todo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class DeleteToDoRequest {
    @NotNull
    @Min(1)
    private Long id;

    @NotBlank(message="userId is mandatory")
    private String userId;
}
