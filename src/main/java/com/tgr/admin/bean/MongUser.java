package com.tgr.admin.bean;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class MongUser {

	@Id
	private String mongUserId;
	
	@NotNull
	private String mongUserName;
	
	@NotNull
	private String mongUserPassWord;
	
	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private Date registrationDate = new Date();
	
	public MongUser() {
		super();
	}

	public MongUser(String mongUserId, String mongUserName, String mongUserPassWord, String name, String email,
			Date registrationDate, Set<String> roles) {
		super();
		this.mongUserId = mongUserId;
		this.mongUserName = mongUserName;
		this.mongUserPassWord = mongUserPassWord;
		this.name = name;
		this.email = email;
		this.registrationDate = registrationDate;
	}
	
	
	
}
