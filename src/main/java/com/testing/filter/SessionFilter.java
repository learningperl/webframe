package com.testing.filter;
  
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;
  
/**
 * 登录过滤
 * 
 * @author william
 * @date 2019-3-7
 */
public class SessionFilter extends OncePerRequestFilter {
  
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
  
        // 请求的uri
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(true);
        System.out.println(session.getId());
        System.out.println(session.getAttribute(session.getId()));
		String userid = "null";
		try {
			userid = session.getAttribute(session.getId()).toString();
		} catch (Exception e) {
		}
		if(uri.equals("/webframe/")) {
        	//首页处理
//    		System.out.println(userid.getClass().getTypeName());
        	if(userid == null || !userid.contains("true-")) {
        		;
        	}else {
        		response.sendRedirect("./pages/");
        		return;
        	}
        }
  
        // uri中包含background时才进行过滤
        if (uri.indexOf("pages") != -1) {
            // 执行过滤
            // 从session中获取登录者实体
            if (userid == null || !userid.contains("true-")) {
                // 如果session中不存在登录者实体，则弹出框提示重新登录
                // 设置request和response的字符集，防止乱码
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.sendRedirect("../");
            } else {
                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果uri中不包含background，则继续
            filterChain.doFilter(request, response);
        }
    }
  
}