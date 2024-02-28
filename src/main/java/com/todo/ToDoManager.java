package com.todo;

import com.todo.model.ToDo;
import com.todo.model.ToDoStatus;
import com.todo.model.User;
import com.todo.repo.ToDoRepository;
import com.todo.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class ToDoManager {

    public static void main(String[] args) {
        SpringApplication.run(ToDoManager.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, ToDoRepository toDoRepository) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return args -> {
            userRepository.save(new User("tester"));

            toDoRepository.save(new ToDo("1_work out", new Date(), "tester"));
            toDoRepository.save(new ToDo("2_tennis", new Date(), "tester"));
        };

    }
}
