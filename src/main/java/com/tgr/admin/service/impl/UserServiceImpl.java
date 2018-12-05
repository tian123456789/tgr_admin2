package com.tgr.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tgr.admin.bean.SecurityUser;
import com.tgr.admin.entity.User;
import com.tgr.admin.repository.UserRepository;
import com.tgr.admin.service.UserService;

@Component
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;

}
