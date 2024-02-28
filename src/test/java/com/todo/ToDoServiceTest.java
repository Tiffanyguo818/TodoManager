package com.todo;

import com.todo.dto.DeleteToDoRequest;
import com.todo.dto.UpdateToDoRequest;
import com.todo.model.ToDo;
import com.todo.repo.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {
    private static final String USER_ID = "tester";
    private static final String TODO_NAME = "my_first_todo";

    @Mock
    private ToDoRepository toDoRepository;
    @InjectMocks
    private ToDoService toDoService;

    @Test
    public void updateToDo_ToDoNotExist() {
        UpdateToDoRequest request = new UpdateToDoRequest();
        request.setUserId(USER_ID);
        request.setId(1l);

        when(toDoRepository.findById(request.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ToDoManagerException.class, () -> {
            toDoService.updateToDo(request);
        });
        assertEquals(String.format("ToDo with ID %s does not exist under user tester.", request.getId()), exception.getMessage());
    }

    @Test
    public void updateToDo_Success() throws ToDoManagerException{
        UpdateToDoRequest request = new UpdateToDoRequest();
        request.setUserId(USER_ID);
        request.setId(1l);
        request.setDescription("");

        ToDo todo = new ToDo(TODO_NAME, new Date(), USER_ID);

        when(toDoRepository.findById(request.getId())).thenReturn(Optional.of(todo));
        assertEquals(String.format("Successfully update ToDo with ID %s for the user tester.", request.getId()), toDoService.updateToDo(request));
    }

    @Test
    public void deleteToDo_ToDoNotExist() {
        DeleteToDoRequest request = new DeleteToDoRequest();
        request.setUserId(USER_ID);
        request.setId(1l);

        when(toDoRepository.existsById(request.getId())).thenReturn(false);
        Exception exception = assertThrows(ToDoManagerException.class, () -> {
            toDoService.deleteToDo(request);
        });
        assertEquals(String.format("ToDo with ID %s does not exist under user tester.", request.getId()), exception.getMessage());
    }

    @Test
    public void deleteToDo_Success() throws ToDoManagerException{
        DeleteToDoRequest request = new DeleteToDoRequest();
        request.setUserId(USER_ID);
        request.setId(1l);

        when(toDoRepository.existsById(request.getId())).thenReturn(true);
        assertEquals(String.format("Successfully delete ToDo with ID %s for the user tester.", request.getId()), toDoService.deleteToDo(request));
    }
}
