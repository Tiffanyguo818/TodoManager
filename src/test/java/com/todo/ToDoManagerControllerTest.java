package com.todo;

import com.todo.dto.CreateToDoRequest;
import com.todo.repo.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ToDoManagerControllerTest {
    private static final String USER_ID = "tester";
    private static final String TODO_NAME = "my_first_todo";

    @Mock
    private ToDoRepository toDoRepository;
    @InjectMocks
    private ToDoController toDoController;

    @Test
    public void createToDo_successful() throws ToDoManagerException{
        CreateToDoRequest request = new CreateToDoRequest();
        request.setUserId(USER_ID);
        request.setName(TODO_NAME);
        request.setDueDate(new Date());

        assertEquals(String.format("Successfully create ToDo for the user %s",
                USER_ID), toDoController.createToDo(request));
    }

}
