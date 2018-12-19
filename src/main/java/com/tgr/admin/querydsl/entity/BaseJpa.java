package com.tgr.admin.querydsl.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author tgr
 *
 * @param <T>
 * @NoRepositoryBean 为了避免SpringDataJpa自动实例化才添加
 */
@NoRepositoryBean
public interface BaseJpa<T> extends JpaRepository<T, Long>,
			JpaSpecificationExecutor<T>,QueryDslPredicateExecutor<T>{

}
