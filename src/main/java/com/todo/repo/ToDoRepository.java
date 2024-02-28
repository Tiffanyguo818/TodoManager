package com.todo.repo;

import com.todo.model.ToDo;
import com.todo.model.ToDoStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ToDoRepository extends CrudRepository<ToDo, Long> {
    List<ToDo> findByUserId(String userId);
    List<ToDo> findByUserIdAndStatus(String userId, ToDoStatus status);
    List<ToDo> findByUserIdAndDueDateBefore(String userId, Date dueDate);


    List<ToDo> findByUserIdOrderByNameAsc(String userId);
    List<ToDo> findByUserIdOrderByStatusAsc(String userId);
    List<ToDo> findByUserIdOrderByDueDateAsc(String userId);
}