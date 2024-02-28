package com.todo;

import com.todo.dto.*;
import com.todo.model.ToDo;
import com.todo.repo.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {
    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private ToDoService toDoService;

    @PostMapping("/todo/query")
    public List<ToDo> getToDo(@Valid @RequestBody QueryToDoRequest request) throws ToDoManagerException{
        String userId = request.getUserId();
        if (ToDoAuthenticator.isValidUserId(userId)) {
            if(request.getStatus() != null) {
                return toDoRepository.findByUserIdAndStatus(userId, request.getStatus());
            } else if(request.getDueDate() != null) {
                return toDoRepository.findByUserIdAndDueDateBefore(userId, request.getDueDate());
            } else {
                return toDoRepository.findByUserId(userId);
            }
        }
        throw new ToDoManagerException(String.format("%s is not authenticated to get ToDo", userId));
    }

    @PostMapping("/todo/sort")
    public List<ToDo> sortToDo(@Valid @RequestBody SortToDoRequest request) throws ToDoManagerException{
        String userId = request.getUserId();
        if (ToDoAuthenticator.isValidUserId(userId)) {
            switch (request.getSortType()){
                case NAME: return toDoRepository.findByUserIdOrderByNameAsc(request.getUserId());
                case STATUS: return toDoRepository.findByUserIdOrderByStatusAsc(request.getUserId());
                default: return toDoRepository.findByUserIdOrderByDueDateAsc(request.getUserId());
            }
        }
        throw new ToDoManagerException(String.format("%s is not authenticated to get ToDo", userId));
    }

    @PostMapping("/todo/update")
    public String updateToDo(@Valid @RequestBody UpdateToDoRequest request) throws ToDoManagerException{
        String userId = request.getUserId();
        if (ToDoAuthenticator.isValidUserId(userId)) {
            return toDoService.updateToDo(request);
        }
        throw new ToDoManagerException(String.format("%s is not authenticated to get ToDo", userId));
    }

    @PostMapping("/todo/create")
    public String createToDo(@Valid @RequestBody CreateToDoRequest request) throws ToDoManagerException{
        String userId = request.getUserId();
        if (ToDoAuthenticator.isValidUserId(userId)) {
            toDoRepository.save(new ToDo(request.getName(), request.getDueDate(), request.getUserId()));
            return String.format("Successfully create ToDo for the user %s", request.getUserId());
        }
        throw new ToDoManagerException(String.format("%s is not authenticated to get ToDo", userId));
    }

    @PostMapping("/todo/delete")
    public String deleteToDo(@Valid @RequestBody DeleteToDoRequest request) throws ToDoManagerException{
        String userId = request.getUserId();
        if (ToDoAuthenticator.isValidUserId(userId)) {
            return toDoService.deleteToDo(request);
        }
        throw new ToDoManagerException(String.format("%s is not authenticated to get ToDo", userId));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return String.format("Invalid request: %s.", String.join(", ", errors));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ToDoManagerException.class)
    public String handleToDoManagerException(ToDoManagerException ex) {
        return String.format("Invalid request: %s.", ex.getMessage());
    }
}
