package com.abhishek.todoapp.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.todoapp.entity.Tags;
import com.abhishek.todoapp.entity.Todo;
import com.abhishek.todoapp.entity.User;
import com.abhishek.todoapp.repository.TagRepository;
import com.abhishek.todoapp.repository.ToDoRepository;
import com.abhishek.todoapp.request.AddTagRequest;
import com.abhishek.todoapp.request.AddTodoRequest;
import com.abhishek.todoapp.request.AddUserRequest;

@RestController
@RequestMapping("/tags")
public class TagController {
	
	
	private TagRepository tagRepository;
	public TagController(TagRepository tagRepository, ToDoRepository todoRepository) {
		super();
		this.tagRepository = tagRepository;
		this.todoRepository = todoRepository;
	}
	private ToDoRepository todoRepository;
	
	
	
	
	

	//create todo
	@PostMapping
	public Todo addTodo(@RequestBody AddTodoRequest todoRequest) {
		Todo todo = new Todo();
		todo.setContent(todoRequest.getContent());
		todo.setTitle(todoRequest.getTitle());
		return todoRepository.save(todo);
		
		
	}
	
	//get tags
    @GetMapping
    public List<Tags> listAllTags() {
        return tagRepository.findAll();
    }
  
    
    //**/notes/{noteId}/addTag**: To add a tag to a specific note.
//    @PostMapping("/todos/{todoId}/addTag")
//    public void addTagTodo(@PathVariable Long todoId, @RequestBody AddTagRequest tagRequest ) {
//    	
//    	Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NoSuchElementException());
//        Tags tag = new Tags();
//        
//        tag.setName(tagRequest.getName());
//        todo.getTags().add(tag);
//        todoRepository.save(todo);
//        
//        
//    	
//    }
    @PostMapping("/todos/{todoId}/addTag")
    public void addTagTodo(@PathVariable Long todoId, @RequestBody AddTagRequest tagRequest ) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NoSuchElementException());
        
        Tags tag = new Tags();
        tag.setName(tagRequest.getName());
        
        // Save the Tags entity to the database
        tagRepository.save(tag);
        
        // Add the saved Tags entity to the todo's tags list
        todo.getTags().add(tag);
        
        // Save the todo with the new tag association
        todoRepository.save(todo);
    }

    

    //**/notes/{noteId}/removeTag**: To remove a tag from a specific note.
    @PostMapping("/todos/{todoId}/removeTag/{tagId}")
    public void removeTagFromTodo(@PathVariable Long todoId , @PathVariable Long tagId) {
    	Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NoSuchElementException());
    	Tags tag = tagRepository.findById(tagId).orElseThrow(()-> new NoSuchElementException());
    	
    	todo.getTags().remove(tag);
    	tagRepository.delete(tag);
    	
    }
    
    //**/notes/tag/{tagName}**: To retrieve all notes associated with a specific tag.
	   @GetMapping("/todos/tag/{tagName}")
	    public List<Todo> getNotesByTag(@PathVariable String tagName) {
	        return todoRepository.findByTagsName(tagName);
	    }
	        	
	  }
//	    
    
    
    
    
    
	
	
	



