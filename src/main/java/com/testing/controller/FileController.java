package com.testing.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.testing.common.MyLogger;
import com.testing.entity.User;
import com.testing.service.impl.UploadManager;

@Controller
public class FileController {

	@Autowired
	private UploadManager uploadService;

	@RequestMapping(value = "/user/upload", method = RequestMethod.GET)
	public String uploadIndex(Model model) throws Exception {
		return "404";
	}

	/*
	 * 采用spring提供的上传文件的方法
	 */
	@RequestMapping(value = "/user/upload", method = RequestMethod.POST)
	public void springUpload(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		session.setMaxInactiveInterval(36000);
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}

		if (userid != null && userid.contains("true-")) {
			String path = MyLogger.path;
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
				MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
				// 获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();

				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						String filename = file.getOriginalFilename();
						String sufix = filename.substring(filename.lastIndexOf(".") + 1);
						MyLogger.logger.info(sufix);
						userid = userid.substring(5, userid.length());
						if (sufix.equals("xlsx") || sufix.equals("xls")) {
							if (sufix.equals("xlsx"))
								path += "\\" + userid + "-tmp.xlsx";
							else
								path += "\\" + userid + "-tmp.xls";
							// 上传
							MyLogger.logger.info(path);
							file.transferTo(new File(path));
							uploadService.AddCase(Integer.parseInt(userid), filename, path);
						} else {
							response.getWriter().print("{\"status\":201,\"msg\":\"用例上传失败，文件类型错误\"}");
							return;
						}
					}

				}

			} else {
				response.getWriter().print("{\"status\":202,\"msg\":\"未接收到文件\"}");
			}
			response.getWriter().print("{\"status\":200,\"msg\":\"用例上传成功\"}");
		} else {
			response.getWriter().print("{\"status\":203,\"msg\":\"非法请求\"}");
		}
	}

	@RequestMapping(value = "/user/download", method = RequestMethod.GET)
	public String LogoutIndex(Model model) throws Exception {
		return "404";
	}

	@RequestMapping(value = "/user/download", method = RequestMethod.POST)
	public void SetUserInfo(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {

	}

	@RequestMapping(value = "/user/uploadres", method = RequestMethod.GET)
	public String uploadresIndex(Model model) throws Exception {
		return "404";
	}

	/*
	 * 采用spring提供的上传文件的方法
	 */
	@RequestMapping(value = "/user/uploadres", method = RequestMethod.POST)
	public void springUploadres(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		session.setMaxInactiveInterval(36000);
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}

		if (userid != null && userid.contains("true-")) {
			userid = userid.substring(5, userid.length());
			String caseid = "null";
			try {
				caseid = session.getAttribute(userid).toString();
			} catch (Exception e) {
			}

			if (caseid != null && caseid.contains("true-")) {
				System.out.println(caseid);
				caseid = caseid.substring(5, caseid.length());
				String path = MyLogger.path;
				// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
				CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
						request.getSession().getServletContext());
				// 检查form中是否有enctype="multipart/form-data"
				if (multipartResolver.isMultipart(request)) {
					// 将request变成多部分request
					MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
					MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
					// 获取multiRequest 中所有的文件名
					Iterator<String> iter = multiRequest.getFileNames();
					while (iter.hasNext()) {
						// 一次遍历所有文件
						MultipartFile file = multiRequest.getFile(iter.next().toString());
						if (file != null) {
							String filename = file.getOriginalFilename();
							String sufix = filename.substring(filename.lastIndexOf(".") + 1);
							MyLogger.logger.info(sufix);
							if (sufix.equals("xlsx") || sufix.equals("xls")) {
								if (sufix.equals("xlsx"))
									path += "\\" + userid + "-tmp.xlsx";
								else
									path += "\\" + userid + "-tmp.xls";
								// 上传
								MyLogger.logger.info(path);
								file.transferTo(new File(path));
								uploadService.AddRes(Integer.parseInt(userid), Integer.parseInt(caseid), filename,
										path);
							} else {
								response.getWriter().print("{\"status\":201,\"msg\":\"结果上传失败，文件类型错误\"}");
								return;
							}
						}
					}
					response.getWriter().print("{\"status\":200,\"msg\":\"结果上传成功\"}");
				}else {
					response.getWriter().print("{\"status\":202,\"msg\":\"未接收到文件\"}");
				}

			} else {
				response.getWriter().print("{\"status\":204,\"msg\":\"服务错误，未关联用例\"}");
			}
		} else {
			response.getWriter().print("{\"status\":203,\"msg\":\"非法请求\"}");
		}
	}

}
