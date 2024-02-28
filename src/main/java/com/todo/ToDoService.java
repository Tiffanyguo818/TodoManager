package com.todo;

import com.todo.dto.DeleteToDoRequest;
import com.todo.dto.UpdateToDoRequest;
import com.todo.model.ToDo;
import com.todo.repo.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public String updateToDo(UpdateToDoRequest request) throws ToDoManagerException{
        Optional<ToDo> result = toDoRepository.findById(request.getId());
        if(result.isEmpty()){
            throw new ToDoManagerException(String.format("ToDo with ID %s does not exist under user %s.", request.getId(), request.getUserId()));
        }
        result.ifPresent(todo -> {
            if (request.getName()!=null && !request.getName().trim().isEmpty()) {
                todo.setName(request.getName());
            }
            if (request.getDescription()!=null) {
                todo.setDescription(request.getDescription());
            }
            if (request.getDueDate()!=null) {
                todo.setDueDate(request.getDueDate());
            }
            if (request.getStatus()!=null) {
                todo.setStatus(request.getStatus());
            }
        });

        toDoRepository.save(result.get());
        return String.format("Successfully update ToDo with ID %s for the user %s.", request.getId(), request.getUserId());
    }

    public String deleteToDo(DeleteToDoRequest request) throws ToDoManagerException{
        if(!toDoRepository.existsById(request.getId())){
            throw new ToDoManagerException(String.format("ToDo with ID %s does not exist under user %s.", request.getId(), request.getUserId()));
        }
        toDoRepository.deleteById(request.getId());
        return String.format("Successfully delete ToDo with ID %s for the user %s.", request.getId(), request.getUserId());
    }
}
