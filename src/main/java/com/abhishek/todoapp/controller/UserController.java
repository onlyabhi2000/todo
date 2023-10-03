package com.abhishek.todoapp.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.todoapp.entity.Todo;
import com.abhishek.todoapp.entity.User;
import com.abhishek.todoapp.repository.ToDoRepository;
import com.abhishek.todoapp.repository.UserRepository;
import com.abhishek.todoapp.request.AddTodoRequest;
import com.abhishek.todoapp.request.AddUserRequest;

@RestController
@RequestMapping("/users")
 
public class UserController {
	

	 private UserRepository userRepository;
	    
	    private ToDoRepository todoRepository;
	
	
	public UserController(UserRepository userRepository, ToDoRepository todoRepository) {
		super();
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
	}

	
	@GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }
	
	 @PostMapping
	    public User addUser(@RequestBody AddUserRequest userRequest){
	        User user = new User();
	        user.setUsername(userRequest.getUsername());
	        user.setPassword(userRequest.getPassword());
	        return userRepository.save(user);
	    }

	    @PostMapping("/{userId}/todos")
	    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest){
	        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	        Todo todo = new Todo();
	        todo.setContent(todoRequest.getContent());
	        todo.setTitle(todoRequest.getTitle());
	       	        user.getTodoList().add(todo);
	        userRepository.save(user);
	    }

	    @PostMapping("/todos/{todoId}")
	    public void toggleTodoCompleted( @PathVariable Long todoId){
	        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
	        todo.setCompleted(!todo.getCompleted());
	        todoRepository.save(todo);
	    }


	    @DeleteMapping("{userId}/todos/{todoId}")
	    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoId){
	        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
	        user.getTodoList().remove(todo);
	        todoRepository.delete(todo);
	    }

	    @DeleteMapping("/{userId}")
	    public void deleteUser(@PathVariable Long userId){
	        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	        userRepository.delete(user);
	    }

}
