package com.testing.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.testing.entity.Rank;
import com.testing.entity.User;
import com.testing.service.impl.CasesManager;
import com.testing.service.impl.RankManager;
import com.testing.service.impl.ResManager;
import com.testing.service.impl.UserManager;

@Controller
public class UserController {

	@Autowired
	private UserManager userService;
	@Autowired
	private RankManager rankService;
	@Autowired
	private CasesManager casesService;
	@Autowired
	private ResManager resService;
	

	@RequestMapping(value = "/user/getuserinfo", method = RequestMethod.GET)
	public String LoginIndex(Model model) throws Exception {
		return "404";
	}

	@RequestMapping(value = "/user/getuserinfo", method = RequestMethod.POST)
	public void GetUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}
		String json;
		if (userid == null || !userid.contains("true-")) {
			json = "{\"status\":203,\"msg\":\"您还未登录!\"}";
		} else {
			userid = userid.substring(5,userid.length());
			System.out.println(userid);
			User res = userService.UserInfo(Integer.parseInt(userid));
			if(res!=null) {
				json = "{\"status\":200,\"msg\":\"查询成功\",";
				json += "\"nickname\":\""+res.getNick()+"\",\"descripe\":\""+res.getDesc()+"\",\"img\":\""+res.getImg()+"\"}";
			}else {
				json = "{\"status\":201,\"msg\":\"未查询到用户信息\"}";
			}
		}
		response.getWriter().print(json);
	}
	
	@RequestMapping(value = "/user/setuserinfo", method = RequestMethod.GET)
	public String LogoutIndex(Model model) throws Exception {
		return "404";
	}

	@RequestMapping(value = "/user/setuserinfo", method = RequestMethod.POST)
	public void SetUserInfo(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}
		String json;
		if (userid == null || !userid.contains("true-")) {
			json = "{\"status\":203,\"msg\":\"您还未登录!\"}";
		} else {
			userid = userid.substring(5,userid.length());
			user.setId(Integer.parseInt(userid));
			System.out.println("昵称：" + user.getNick() + " 密码：" + user.getDesc() + " 密码：" + user.getId());
			userService.Updater(user);
			json = "{\"status\":200,\"msg\":\"用户信息修改成功\"}";
			System.out.println(json);
			// response.sendRedirect("static/ReportForms.html");
		}
		response.getWriter().print(json);
	}
	
	@RequestMapping(value = "/user/getrank", method = RequestMethod.POST)
	public String RankIndex(Model model) throws Exception {
		return "404";
	}

	@RequestMapping(value = "/user/getrank", method = RequestMethod.GET)
	public void GetRank(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}
		String json;
		if (userid == null || !userid.contains("true-")) {
			json = "{\"status\":203,\"msg\":\"非法请求!\"}";
		} else {
			List<Rank> list = rankService.RankList();
			if (list==null) {
				json = "{\"status\":200,\"msg\":\"查询成功\","
						+ "\"ucount\":" + userService.getUserCount() + ","
						+ "\"ccount\":" + casesService.getCaseCount() + ","
						+ "\"rcount\":" + resService.getResCount() + ","
						+ "\"ranklist\":\"[]\""
						+ "}";
			}else {
				List<User> users = new ArrayList<User>();
				for (int i=0;i<list.size();i++) {
					User user = userService.UserInfo(list.get(i).getUserId());
					user.setPwd(null);
					users.add(user);
				}
				json = "{\"status\":200,\"msg\":\"查询成功\","
						+ "\"ucount\":" + userService.getUserCount() + ","
						+ "\"ccount\":" + casesService.getCaseCount() + ","
						+ "\"rcount\":" + resService.getResCount() + ","
						+ "\"ranklist\":" + JSONObject.toJSONString(list)+ ","
						+ "\"userlist\":" + JSONObject.toJSONString(users)
						+ "}";
			}
		}
		response.getWriter().print(json);
	}
	
}
