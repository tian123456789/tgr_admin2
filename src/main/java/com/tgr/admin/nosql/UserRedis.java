package com.tgr.admin.nosql;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.tgr.admin.entity.User;

/**
 * @author tgr
 *	应用redis作为存储的 User______Repository
 *
 *该缓存可以对复杂的关联对象那个进行缓存
 */
@Repository
public class UserRedis {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public void add(String key,Long time,User user,TimeUnit unit) {
		Gson gson = new Gson();
		redisTemplate.opsForValue().set(key, gson.toJson(user), time, unit);
	}
	
	public void add(String key,Long time,List<User> users,TimeUnit unit) {
		Gson gson = new Gson();
		redisTemplate.opsForValue().set(key,gson.toJson(users),time , unit);;
	}
	
	public User get(String key) {
		Gson gson = new Gson();
		User user = null;
		String userJson = (String) redisTemplate.opsForValue().get(key);
		if(StringUtils.isNoneBlank(userJson)) {
			user = gson.fromJson(userJson, User.class);
			return user;
		}
		return user;
	}
	
	public List<User> getList(String key){
		Gson gson = new Gson();
		List<User> users = null;
		String listJson = (String) redisTemplate.opsForValue().get(key);
		if(StringUtils.isNoneBlank(listJson)) {
			users = gson.fromJson(listJson, new TypeToken<List<User>>() {
				private static final long serialVersionUID = 1L;
			}.getType());
			return users;
		}
		return users;
		
	}
	
	public void delete(String key) {
		redisTemplate.opsForValue().getOperations().delete(key);
	}
	
}
