package com.abhishek.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.abhishek.todoapp.entity.Tags;
import com.abhishek.todoapp.entity.Todo;
import com.abhishek.todoapp.entity.User;
import com.abhishek.todoapp.repository.TagRepository;
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
    
    @Autowired
    private TagRepository tagRepository;


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//4oct
		//create tags
		Tags tag1 = new Tags();
		tag1.setName("#nicedayagain6");
		tagRepository.save(tag1);
		
//		Tags tag2= new Tags();
//		tag1.setName("#Food");
//		tagRepository.save(tag2);
		
		
		//1st oct
		//create user
		User user = new User();
        user.setPassword("password");
        user.setUsername("Neeraj");
//crete todo
        Todo todo  = new Todo();
        todo.setContent("Upload this to github");
        todo.setTitle("java");
        
        todo.getTags().add(tag1);
        todo.getTags().add(tag1)
;        
        
        user.getTodoList().add(todo);
        
        todoRepository.save(todo);
        userRepository.save(user);
		
	}

}
