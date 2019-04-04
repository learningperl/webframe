package com.testing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RunController {


	@RequestMapping(value = "/user/run", method = RequestMethod.POST)
	public String LoginIndex(Model model) throws Exception {
		return "404";
	}

	@RequestMapping(value = "/user/run", method = RequestMethod.GET)
	public void GetUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String caseid = request.getParameter("caseid");
		String json;
		if (caseid==null) {
			json = "{\"status\":201,\"msg\":\"您还未登录!\"}";
		}else {
			HttpSession session = request.getSession();
			String userid = "null";
			try {
				userid = session.getAttribute(session.getId()).toString();
			} catch (Exception e) {
			}
			if (userid == null || !userid.contains("true-")) {
				json = "{\"status\":203,\"msg\":\"您还未登录!\"}";
			} else {
				userid = userid.substring(5,userid.length());
				session.setAttribute(userid, "true-" + caseid);
				json = "{\"status\":200,\"msg\":\"运行信息已经设置\"}";
			}
		}
		response.getWriter().print(json);
	}
	
}
