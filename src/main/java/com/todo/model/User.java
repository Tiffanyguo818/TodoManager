package com.todo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    private String userId;

    @Getter @Setter
    @OneToMany(mappedBy = "userId")
    private List<ToDo> toDoList;
    public User(String userId){
        this.userId = userId;
    }
}
