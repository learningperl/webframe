package com.testing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.testing.entity.User;
import com.testing.service.impl.UserManager;

@Controller
public class LoginController {

	@Autowired
	private UserManager userService;

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String LoginIndex(Model model) throws Exception {
		return "404";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public void Login(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("用户名：" + user.getUserName() + "密码：" + user.getPwd());

		HttpSession session = request.getSession();
//		session.setMaxInactiveInterval(36000);
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}
		String json;
		if (userid == null || !userid.contains("true-")) {
			User res = userService.Login(user);
			if (res != null) {
				String uid = res.getId().toString();
				session.setAttribute(session.getId(),"true-" + uid);
				// System.out.println(session.getAttribute(session.getId()));
				json = "{\"status\":200,\"msg\":\"恭喜您，登录成功\"";
				json += ",\"sessionid\":\"" + session.getId() + "\"}";
				System.out.println(json);
				// response.sendRedirect("static/ReportForms.html");
			} else
				// System.out.println(json);
				json = "{\"status\":201,\"msg\":\"用户名密码错误\"}";
		} else {
			// System.out.println(json);
			json = "{\"status\":203,\"msg\":\"不能重复登录!\"}";
			// response.sendRedirect("static/KeyWords.html");
		}
		response.getWriter().print(json);
	}
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public String LogoutIndex(Model model) throws Exception {
		return "404";
	}

	@RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	public void Logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String json = "";		
		HttpSession session = request.getSession();
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}
		
		if (userid.contains("true-")){
			json = "{\"status\":200,\"msg\":\"注销成功!\"}";
		}else{
			json = "{\"status\":200,\"msg\":\"登录态已经失效。\"}";
		}
		//session.setAttribute(session.getId(), null);
		session.invalidate();

		response.getWriter().print(json);
	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public String RegistIndex(Model model) throws Exception {
		return "404";
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public void Regist(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("用户名：" + user.getUserName() + "密码：" + user.getPwd());

		HttpSession session = request.getSession();
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}
		String json;
		if (userid == null || !userid.contains("true-")) {
			userService.Register(user);
			json = "{\"status\":200,\"msg\":\"恭喜您，注册成功\"}";
			System.out.println(json);
		} else {
			// System.out.println(json);
			json = "{\"status\":203,\"msg\":\"您已经登录，不能注册\"}";
			// response.sendRedirect("static/KeyWords.html");
		}
		response.getWriter().print(json);
	}

}
