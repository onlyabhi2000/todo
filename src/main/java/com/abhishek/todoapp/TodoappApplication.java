package com.abhishek.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.abhishek.todoapp.entity.Todo;
import com.abhishek.todoapp.entity.User;
import com.abhishek.todoapp.repository.ToDoRepository;
import com.abhishek.todoapp.repository.UserRepository;

@SpringBootApplication
public class TodoappApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ToDoRepository todoRepository;


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		User user = new User();
        user.setPassword("password");
        user.setUsername("Abhishek");

        Todo todo  = new Todo();
        todo.setContent("Upload this to github");
        todo.setTitle("java");

        user.getTodoList().add(todo);
        
        todoRepository.save(todo);
        userRepository.save(user);
		
	}

}
