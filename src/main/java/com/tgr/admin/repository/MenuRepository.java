package com.tgr.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tgr.admin.entity.Menu;


@Repository
public interface MenuRepository extends PagingAndSortingRepository<Menu, Long> {

	List<Menu> findByLevelOrderBySortAsc(int level);

	List<Menu> findByIdIn(List<Long> menuIds);

	@Query(value = "SELECT m.* FROM role_menu rm JOIN menu m ON rm.menu_id = m.id AND rm.role_id =  ?",nativeQuery = true)
	List<Menu> findByRoleId(long id);
}
