package com.abhishek.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.todoapp.entity.Todo;

@Repository
public interface ToDoRepository  extends JpaRepository<Todo, Long>{

}
