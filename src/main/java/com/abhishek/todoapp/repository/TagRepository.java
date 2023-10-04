package com.abhishek.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.todoapp.entity.Tags;

public interface TagRepository  extends JpaRepository<Tags, Long> {

}
