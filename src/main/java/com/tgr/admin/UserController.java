package com.tgr.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tgr.admin.entity.Department;
import com.tgr.admin.entity.Role;
import com.tgr.admin.entity.User;
import com.tgr.admin.model.UserQo;
import com.tgr.admin.nosql.UserRedis;
import com.tgr.admin.repository.DepartmentRepository;
import com.tgr.admin.repository.RoleRepository;
import com.tgr.admin.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Api(tags = {"嗨，君上_田国瑞"})
//@RestController
@Controller
@RequestMapping(value = "user")
@SuppressWarnings("all")
//@PreAuthorize("hasAnyRole('陆军部总长','海军部总长')")//hasAnyAuthorize
public class UserController extends BaseController{
	
	private Log log = LogFactory.getLog(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private RoleRepository roleRepository;	
    
	@Autowired
	private UserRedis userRedis;

	/*@Value("${securityconfig.urlroles}")
	private String urlRoles;*/
	
	
    @PreAuthorize("hasAuthority('HJ1')")//数据库不用加ROLE_ or hasRole('海军部总长') '海军部总长' ROLE_海军部总长
	@RequestMapping("/index")
	public String index(Model model,Principal user) {
		
		// urlroles: /**/new = ,manage,admin;
    	//		  	 /**/edit/** = admin;
    	//		 	 /**/delete/** = admin
		
		/*Authentication authentication = (Authentication) user;
		List<String> user_role_list = new ArrayList<String>();
		for(GrantedAuthority ga :authentication.getAuthorities()) {
			user_role_list.add(ga.getAuthority());
		}
		boolean newRole = false,editRole = false,deleteRole = false;
		if(!StringUtils.isEmpty(urlRoles)) {
			String[] resources = urlRoles.split(";");
			for(String resource : resources) {
				String[] urls = resource.split("=");
				if(urls[0].indexOf("new") > 0) {
					String[] newRoles = urls[1].split(",");
					for(String str : newRoles) {
						str = str.trim();
						if(user_role_list.contains(str)) {
							newRole = true;
							break;
						}
					}
				}else if(urls[0].indexOf("edit") > 0) {
					String[] editRoles = urls[1].split(",");
					for(String str : editRoles) {
						str = str.trim();
						if(user_role_list.contains(str)) {
							editRole = true;
							break;
						}
					}
				}else if(urls[0].indexOf("delete") > 0) {
					String[] deleteRoles = urls[1].split(",");
					for(String str : deleteRoles) {
						str = str.trim();
						if(user_role_list.contains(str)) {
							deleteRole = true;
							break;
						}
					}
				}
			}
		}
		
		model.addAttribute("newrole", newRole);
		model.addAttribute("editrole", editRole);
		model.addAttribute("deleterole", deleteRole);*/
		
		//model.addAttribute("user", user);
		
		//roles
		//departments
		//users
		List<User> users = userRepository.findAll();
		List<Department> departments =departmentRepository.findAll();
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("users", users);
		model.addAttribute("departments", departments);
		model.addAttribute("roles", roles);
		model.addAttribute("returnDept", departmentRepository.findAll().get(0));
		//roleNames
		
		return "user/index";
	}
	
	@ApiOperation(value = "来吧，加入点大臣")
	@PostMapping(value = "post")
	public String post(HttpServletRequest request,
			@RequestParam(required = true , defaultValue = "-1") Long departmentId,
			@RequestParam(required = true) String username,
			@RequestParam(required = true,defaultValue = "root") String password,
			@RequestParam(required = true,defaultValue = "1") int sex,
			@RequestParam(name = "roleIds") List<Long> roleIds){
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setSex(sex);
		
		if(departmentId != -1) {
			user.setDepartment(departmentRepository.findOne(departmentId));
		}
		
		if(roleIds != null && roleIds.size() > 0) {
			List<Role> roles = roleRepository.findByIdIn(roleIds);
			if(roles.size() > 0) {
				user.setRoles(roles);
			}
		}
		
		User returnUser = userRepository.save(user);
		return "redirect:/user/index";
	}
	
	 @RequestMapping(value="user_put")
	    public String update(HttpServletRequest request,
	    		@RequestParam(required = true) Long userId,
	    		@RequestParam(required = true , defaultValue = "-1") Long departmentId,
				@RequestParam(required = true) String username,
				@RequestParam(required = true,defaultValue = "root") String password,
				@RequestParam(required = true,defaultValue = "1") int sex,
				@RequestParam(name = "roleIds") List<Long> roleIds){
	        User user = userRepository.findOne(userId);
	        
	        user.setUsername(username);
			user.setSex(sex);
			
			if(departmentId != -1) {
				user.setDepartment(departmentRepository.findOne(departmentId));
			}
			
			if(roleIds != null && roleIds.size() > 0) {
				List<Role> roles = roleRepository.findByIdIn(roleIds);
				if(roles.size() > 0) {
					user.setRoles(roles);
				}
			}
			
			User returnUser = userRepository.save(user);

	        return "redirect:/user/index";
	    }
	 
	 	//@PreAuthorize("hasAnyAuthorize('陆军部总长','海军部总长')")
	 	@RequestMapping(value="user_del",method = RequestMethod.POST)
	    public String delete(@RequestParam(required = true) Long id) throws Exception{
	 		User user = userRepository.findById(id);
	 		user.setRoles(null);
	 		user.setDepartment(null);
	 		User u = userRepository.save(user);
	        userRepository.delete(u);
	        log.info("删除->ID="+id);
	        return "redirect:/user/index";
	    }
	 
	 	@RequestMapping(value = "users")
	    public String query(Model model,@RequestParam(required = true) Long departmentId) {
	    	Department departParment = departmentRepository.findOne(departmentId);
	    	List<User> users = userRepository.findByDepartment(departParment);
			List<Department> departments =departmentRepository.findAll();
			List<Role> roles = roleRepository.findAll();
			model.addAttribute("users", users);
			model.addAttribute("departments", departments);
			model.addAttribute("roles", roles);
			model.addAttribute("returnDept", departmentRepository.findAll().get(0));
			//roleNames
			
			return "user/index";
	    }

	
	 	@RequestMapping(value="/{id}")
	    public String show(ModelMap model,@PathVariable Long id) {
	        User user = userRepository.findOne(id);
	        model.addAttribute("user",user);
	        return "user/show";
	    }

	    @RequestMapping(value = "/list")
	    @ResponseBody
	    public Page<User> getList(UserQo userQo) {
	        try {
	        	int page = userQo.getPage();
	        	int size = userQo.getSize();
	            Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
	            return userRepository.findByUsername(userQo.getName()==null?"%":"%"+userQo.getName()+"%", pageable);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return null;
	    }

	    @RequestMapping("/new")
	    public String create(ModelMap model,User user){
	        List<Department> departments = departmentRepository.findAll();
	        List<Role> roles = roleRepository.findAll();

	        model.addAttribute("departments",departments);
	        model.addAttribute("roles", roles);
	        model.addAttribute("user", user);
	        return "user/new";
	    }

	    @RequestMapping(value="/save", method = RequestMethod.POST)
	    @ResponseBody
	    public String save(User user) throws Exception{
	        user.setCreatedate(new Date());
	        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
	        user.setPassword(bpe.encode(user.getPassword()));
	        userRepository.save(user);
	        log.info("新增->ID="+user.getId());
	        return "1";
	    }

	@ApiOperation(value = "测试redis")
	@GetMapping(value = "testRedis")
	public ResponseResult<User> testRedis(){
		
		Role role = new Role();
		role.setName("陆军部大臣");
		
		User user = new User();
		
		List<Role> roles = new ArrayList<Role>();
		
		userRedis.delete(user.getUsername());
		userRedis.add(user.getUsername(), 1L, user, TimeUnit.HOURS);
		
		User user1 = userRedis.get("田二蛋");
		
		return ResponseResult.getResult(user1);
	}
	
}
