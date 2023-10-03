package com.abhishek.todoapp.request;

import lombok.Data;

@Data

public class AddUserRequest {
	
	public AddUserRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	private String username;
	private String password;
	

}
