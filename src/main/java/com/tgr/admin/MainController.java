package com.tgr.admin;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tgr.admin.entity.User;
import com.tgr.admin.nosql.RedisService;
import com.tgr.admin.repository.UserRepository;
import com.tgr.admin.util.ImageCode;

import io.swagger.annotations.Api;

@Api(tags = {"登录接口"})
@Controller
@EnableAutoConfiguration
@SuppressWarnings("all")
public class MainController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RedisService<Object> redisSevice;
	
	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response,Model model) {
		return "index";
	}
	
	@GetMapping("/401")
	public String accessDenied() {
		return "401";
	}
	
	@RequestMapping("/success")
	public String success(HttpServletRequest request,HttpServletResponse response,Model model) {
		
		return "success";
	}
	
	@RequestMapping("/fail")
	public String fail(HttpServletRequest request,HttpServletResponse response,Model model) {
		return "fail";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,Model model) {
		return "login";
	}
	
	@RequestMapping("/to_login")
	public String toLogin(HttpServletRequest request,HttpServletResponse response,Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/images/imagecode")
	public String imagecode(HttpServletRequest request,HttpServletResponse response) {
		
		OutputStream os;
		try {
			os = response.getOutputStream();
			java.util.Map<String, Object> map = ImageCode.getImageCode(60, 20, os);
			String simpleCaptcha = "simpleCaptcha";
			request.getSession().setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
			ImageIO.write((RenderedImage) map.get("image"), "JPEG", os);
		} catch (IOException e1) {
			e1.printStackTrace();
			return "";
		}
		return null;
	}
	
	@RequestMapping(value = "/checkcode")
	@ResponseBody
	public String checkcode(HttpServletRequest request,HttpSession session) {
		
		/*String checkCode = request.getParameter("checkCode");
		Object cko = session.getAttribute("simpleCaptcha");
		if(cko == null) {
			request.setAttribute("errorMsg", "验证码已失效,请重新输入! ");
			return "验证码已失效，请重新输入!";
		}
		
		String captcha = cko.toString();
		Date now = new Date();
		Long codeTime = Long.valueOf(session.getAttribute("codeTime")+"");
		if(StringUtils.isEmpty(checkCode) || captcha == null || 
				!(checkCode.equalsIgnoreCase(captcha))) {
			request.setAttribute("errorMsg", "验证码错误!");
			return "验证码错误";
		}else if((now.getTime() - codeTime)/1000/60 > 5) {//有效时长5分钟
			request.setAttribute("errorMsg", "验证码已经失效,请重新输入! ");
			return "验证码已经失效请重新输入";
		}else {
			session.removeAttribute("simpleCaptcha");
			return "1";
		}*/
		
		return "1";
	}
	
	public String createToken(Long persion_id) {
		String token = "111111111111111111111111111111111111";//uniqueIdGenerator.getNewId("dlb-admin");
		redisSevice.opsForValue().set(token, persion_id, 7, TimeUnit.DAYS);
		return token;
	}
	
	
	
	
	
	
	
	
	
}
