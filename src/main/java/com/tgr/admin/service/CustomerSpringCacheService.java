package com.tgr.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tgr.admin.entity.Customer;

public interface CustomerSpringCacheService {

	Customer findById(Long id);
	
	Customer create(Customer customer);
	
	Customer update(Customer customer);
	
	void delete(Long id);

	List<Customer> findAll(Pageable pageable);
}
