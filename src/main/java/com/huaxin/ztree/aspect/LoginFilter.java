package com.huaxin.ztree.aspect;

import com.huaxin.ztree.bean.Userinfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		Userinfo user = (Userinfo) request.getSession().getAttribute("userinfo");
		String url = request.getRequestURI();
		
		if (!url.contains("UserinfoController") && !url.contains("index.jsp")) {
			if (user == null) {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}
}
