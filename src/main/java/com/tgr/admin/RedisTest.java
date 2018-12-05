package com.tgr.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.tgr.admin.entity.Role;
import com.tgr.admin.entity.User;
import com.tgr.admin.nosql.UserRedis;

public class RedisTest {
	
	
	static UserRedis userRedis = new UserRedis();
	
	public static void setup() {
		
		Role role = new Role();
		role.setName("陆军部大臣");
		
		User user = new User();
		user.setUsername("田二蛋");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		userRedis.add(user.getUsername(), 1L, user, TimeUnit.HOURS);
	}
	
}
