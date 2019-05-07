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
import com.testing.entity.Mail;
import com.testing.service.impl.MailManager;
import com.testing.service.impl.UserManager;

@Controller
public class MailController {
	@Autowired
	private MailManager mailService;
	
	@Autowired
	private UserManager userService;

	@RequestMapping(value = "/user/getmails", method = RequestMethod.GET)
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
			List<Mail> list1=null;
			//过滤管理员
			if(!userid.equals("1")) {
				list1 = mailService.getMails(Integer.parseInt(userid));
			}
			
			List<Mail> list2 = mailService.getDefaults();
			if (list1==null) {
				list1 = list2;
			}else {
				if(list2!=null) {
					for(int i=0;i<list2.size();i++){
						list1.add(list2.get(i));
					}
				}
			}
			
			if(list1==null) {
				json = "{\"status\":201,\"msg\":\"您还没有添加邮件配置\"}";
			}else {
				json = "{\"status\":200,\"msg\":\"配置获取成功\",\"mails\":";
				String cases = JSONObject.toJSONString(list1);
				json += cases;
				json += "}";
			}
		}
		response.getWriter().print(json);
	}

	@RequestMapping(value = "/user/getmails", method = RequestMethod.POST)
	public String Login(Model model) throws Exception {
		return "404";
	}
	
	@RequestMapping(value = "/user/delmail", method = RequestMethod.GET)
	public void DeleteIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Integer id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id").toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(id);
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
			if(id<=0) {
				json = "{\"status\":202,\"msg\":\"参数错误\"}";
			}else {
				userid = userid.substring(5);
				Integer res = mailService.Delete(id,Integer.parseInt(userid));
				if(res==0) {
					json = "{\"status\":201,\"msg\":\"您不能删除该配置，请联系管理员\"}";
				}else {
					json = "{\"status\":200,\"msg\":\"邮件配置删除成功\"}";
				}
			}
		}
		response.getWriter().print(json);
	}

	@RequestMapping(value = "/user/delmail", method = RequestMethod.POST)
	public String Delete(Model model) throws Exception {
		return "404";
	}
	
	@RequestMapping(value = "/user/addmail", method = RequestMethod.GET)
	public String AddMailIndex(Model model) throws Exception {
		return "404";
	}

	@RequestMapping(value = "/user/addmail", method = RequestMethod.POST)
	public void AddMail(Mail mail, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("名：" + mail.getMName() + " \nHTML：" + mail.getMHtml());

		HttpSession session = request.getSession();
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
			mail.setUserId(Integer.parseInt(userid));
			System.out.println(JSONObject.toJSONString(mail));
			Integer status = mailService.Updater(mail);
			if(status==0) {
				json = "{\"status\":200,\"msg\":\"恭喜您，更新成功\"}";
			}else {
				if(status==2) {
					json = "{\"status\":200,\"msg\":\"恭喜您，添加成功\"}";
				}else {
					if(status==-1) {
						json = "{\"status\":200,\"msg\":\"恭喜您，添加成功\"}";
					}else {
						json = "{\"status\":201,\"msg\":\"添加失败，用户最多只能添加3个配置\"}";
					}
				}
			}
		}
		response.getWriter().print(json);
	}
	
	@RequestMapping(value = "/user/usemail", method = RequestMethod.POST)
	public String UseMailIndex(Model model) throws Exception {
		return "404";
	}

	@RequestMapping(value = "/user/usemail", method = RequestMethod.GET)
	public void UseMail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}
		Integer mailid = 0;
		try {
			mailid = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
		}
		
		String json;
		if (userid == null || !userid.contains("true-")) {
			json = "{\"status\":203,\"msg\":\"非法请求\"}";
		} else {
			userid = userid.substring(5);
			userService.UpdateMailSeting(Integer.parseInt(userid), mailid);
			json = "{\"status\":200,\"msg\":\"设置成功\"}";
		}
		response.getWriter().print(json);
	}
}
