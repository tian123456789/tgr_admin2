package com.tgr.admin.service;


import com.tgr.admin.entity.User;

public interface UserDetailService {

	User loadUserByUserName(String userName);
}
