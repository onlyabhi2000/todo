package com.abhishek.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.todoapp.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

}
