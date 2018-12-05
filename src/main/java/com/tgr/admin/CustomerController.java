package com.tgr.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgr.admin.entity.Customer;
import com.tgr.admin.service.CustomerSpringCacheService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"企业管理"})
@RestController
@RequestMapping(value = "customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerSpringCacheService customerSpringCacheService;
	
	@ApiOperation(value = "创建企业")
	@PostMapping(value = "post")
	public void save(HttpServletRequest request,@RequestParam(required = true) String name,
			@RequestParam(required = false) Long id) {
		Customer customer = new Customer();
		customer.setName(name);
		if(id != null) {
			customer.setId(id);
		}
		customerSpringCacheService.create(customer);
	}
	
	@ApiOperation(value = "ID查询企业")
	@GetMapping(value = "findById")
	public Customer findById(HttpServletRequest request,@RequestParam(required = true) Long id) {
		return customerSpringCacheService.findById(id);
	}
	
	@ApiOperation(value = "修改企业")
	@PostMapping(value = "put")
	public Customer put(HttpServletRequest request,@RequestParam(required = true) Long id,
			@RequestParam(required = true) String name) {
		Customer customer = customerSpringCacheService.findById(id);
		if(customer != null) {
			customer.setName(name);
			return customerSpringCacheService.update(customer);
		}
		return null;
	}
	
	@ApiOperation(value = "根据ID删除企业")
	@PostMapping(value = "deleteById")
	public void delete(HttpServletRequest request,@RequestParam(required = true) Long id) {
		customerSpringCacheService.delete(id);
	}
	
	
	@ApiOperation(value = "企业列表")
	@GetMapping(value = "list")
	public List<Customer> list(HttpServletRequest request,
			@RequestParam(required = false,defaultValue = "0") int pageNo,
			@RequestParam(required = false,defaultValue = "50") int pageSize){
		Pageable pageable = new PageRequest(pageNo, pageSize, new Sort(Direction.DESC, "updateTime"));
		return customerSpringCacheService.findAll(pageable);
	}
	
	
	
}
