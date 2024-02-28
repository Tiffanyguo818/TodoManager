package com.todo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;

    @Getter @Setter
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private ToDoStatus status = ToDoStatus.NOT_STARTED;

    @Getter @Setter
    private String userId;


    public ToDo(String name, Date dueDate, String userId){
        this.name = name;
        this.dueDate = dueDate;
        this.userId = userId;
    }

}
