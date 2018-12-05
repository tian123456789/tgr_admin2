package com.tgr.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tgr.admin.entity.Customer;
import com.tgr.admin.repository.CustomerRepository;
import com.tgr.admin.service.CustomerSpringCacheService;

@Service
public class CustomerSpringCacheServiceImpl implements CustomerSpringCacheService {

	@Autowired
	private CustomerRepository customerRepository;
	
	//simpleKey objectId

	@Override
	@Cacheable(value = "mysql:findById:customer",keyGenerator = "simpleKey")//simpleKey
	public Customer findById(Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	@CachePut(value = {"mysql:findById:customer","mysql:list:customer"},keyGenerator = "objectId")
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	@CachePut(value = {"mysql:findById:customer","mysql:list:customer"},keyGenerator = "objectId")
	public Customer update(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	@CacheEvict(value = {"mysql:findById:customer","mysql:list:customer"},beforeInvocation = true,
												keyGenerator = "simpleKey")
	public void delete(Long id) {
		customerRepository.delete(id);
	}

	@Override
	@Cacheable(value = "mysql:list:customer",keyGenerator = "queryAll")//simpleKey
	public List<Customer> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable).getContent();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
