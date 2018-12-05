package com.tgr.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgr.admin.entity.Department;
import com.tgr.admin.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findById(Long l);

	List<User> findByDepartment(Department departParment);

	User findByUsername(String name);

	Page<User> findByUsername(String object, Pageable pageable);
}
