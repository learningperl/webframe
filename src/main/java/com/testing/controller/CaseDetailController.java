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
import com.testing.entity.CaseDetail;
import com.testing.service.CasesDetailManager;

@Controller
public class CaseDetailController {
	
	@Autowired
	private CasesDetailManager detailService;

	@RequestMapping(value = "/user/getdetails", method = RequestMethod.GET)
	public void LoginIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Integer caseid = 0;
		try {
			caseid = Integer.parseInt(request.getParameter("caseid"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
			List<CaseDetail> list = detailService.getDetails(userid,caseid);
			if(list==null) {
				json = "{\"status\":201,\"msg\":\"该用例不存在\"}";
			}else {
				json = "{\"status\":200,\"msg\":\"用例获取成功\",\"cases\":";
				String cases = JSONObject.toJSONString(list);
				json += cases;
				json += "}";
			}
		}
		response.getWriter().print(json);
	}

	@RequestMapping(value = "/user/getdetails", method = RequestMethod.POST)
	public String Login(Model model) throws Exception {
		return "404";
	}
	
}
