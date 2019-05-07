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
import com.testing.entity.ResDetail;
import com.testing.service.impl.ResDetailManager;
import com.testing.service.impl.ResManager;
import com.testing.entity.Res;

@Controller
public class ResController {

	@Autowired
	private ResManager resService;
	@Autowired
	private ResDetailManager detailService;

	@RequestMapping(value = "/user/getress", method = RequestMethod.GET)
	public void ResIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			List<Res> list = resService.getRess(Integer.parseInt(userid));
			
			if(list==null) {
				json = "{\"status\":201,\"msg\":\"暂无结果文件\"}";
			}else {
				json = "{\"status\":200,\"msg\":\"结果获取成功\",\"ress\":";
				String ress = JSONObject.toJSONString(list);
				json += ress;
				json += "}";
			}
		}
		response.getWriter().print(json);
	}

	@RequestMapping(value = "/user/getress", method = RequestMethod.POST)
	public String Res(Model model) throws Exception {
		return "404";
	}
	
	@RequestMapping(value = "/user/delress", method = RequestMethod.GET)
	public void DeleteIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Integer resid = 0;
		try {
			resid = Integer.parseInt(request.getParameter("id").toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(resid);
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
			if(resid<=0) {
				json = "{\"status\":202,\"msg\":\"参数错误\"}";
			}else {
				userid = userid.substring(5);
				Integer res = resService.Delete(resid,Integer.parseInt(userid));
				if(res==0) {
					json = "{\"status\":201,\"msg\":\"要删除的结果不存在\"}";
				}else {
					detailService.Delete(resid);
					json = "{\"status\":200,\"msg\":\"结果删除成功\"}";
				}
			}
		}
		response.getWriter().print(json);
	}

	@RequestMapping(value = "/user/delress", method = RequestMethod.POST)
	public String Delete(Model model) throws Exception {
		return "404";
	}
	
	@RequestMapping(value = "/user/getresdetail", method = RequestMethod.GET)
	public void ResDetailIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
//		session.setMaxInactiveInterval(36000);
		Integer resid = 0;
		try {
			resid = Integer.parseInt(request.getParameter("id").toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			List<ResDetail> list = detailService.getDetails(userid, resid);
			if(list==null) {
				json = "{\"status\":201,\"msg\":\"用例结果不存在\"}";
			}else {
				json = "{\"status\":200,\"msg\":\"结果获取成功\",\"ress\":";
				String ress = JSONObject.toJSONString(list);
				json += ress;
				json += "}";
			}
		}
		response.getWriter().print(json);
	}

	@RequestMapping(value = "/user/getresdetail", method = RequestMethod.POST)
	public String ResDetail(Model model) throws Exception {
		return "404";
	}
	
}
