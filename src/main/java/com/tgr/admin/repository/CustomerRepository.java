package com.tgr.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgr.admin.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
