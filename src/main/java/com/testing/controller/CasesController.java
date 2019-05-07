package com.testing.controller;

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
import com.testing.entity.Cases;
import com.testing.service.impl.CasesDetailManager;
import com.testing.service.impl.CasesManager;
import com.testing.service.impl.ResManager;

@Controller
public class CasesController {

	@Autowired
	private CasesManager casesService;
	@Autowired
	private ResManager resService;
	@Autowired
	private CasesDetailManager detailService;

	@RequestMapping(value = "/user/getcases", method = RequestMethod.GET)
	public void LoginIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
//		session.setMaxInactiveInterval(36000);
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}
		String json;
		if (userid == null || !userid.contains("true-")) {
			json = "{\"status\":203,\"msg\":\"非法请求\"}";
		} else {
			userid = userid.substring(5);
			List<Cases> list = casesService.getCases(userid);
			if(list==null) {
				json = "{\"status\":201,\"msg\":\"用户暂无用例\"}";
			}else {
				json = "{\"status\":200,\"msg\":\"用例获取成功\",\"cases\":";
				String cases = JSONObject.toJSONString(list);
				json += cases;
				json += "}";
			}
		}
		response.getWriter().print(json);
	}

	@RequestMapping(value = "/user/getcases", method = RequestMethod.POST)
	public String Login(Model model) throws Exception {
		return "404";
	}
	
	@RequestMapping(value = "/user/delcases", method = RequestMethod.GET)
	public void DeleteIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Integer caseid = 0;
		try {
			caseid = Integer.parseInt(request.getParameter("id").toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(caseid);
		HttpSession session = request.getSession();
//		session.setMaxInactiveInterval(36000);
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}
		String json;
		if (userid == null || !userid.contains("true-")) {
			json = "{\"status\":203,\"msg\":\"非法请求\"}";
		} else {
			if(caseid<=0) {
				json = "{\"status\":202,\"msg\":\"参数错误\"}";
			}else {
				userid = userid.substring(5);
				Integer res = casesService.Delete(caseid,Integer.parseInt(userid));
				if(res==0) {
					json = "{\"status\":201,\"msg\":\"要删除的用例不存在\"}";
				}else {
					resService.Delete(caseid);
					detailService.Delete(caseid);
					json = "{\"status\":200,\"msg\":\"用例删除成功\"}";
				}
			}
		}
		response.getWriter().print(json);
	}

	@RequestMapping(value = "/user/delcases", method = RequestMethod.POST)
	public String Delete(Model model) throws Exception {
		return "404";
	}
	
}
