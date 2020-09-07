package com.erp.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class MyFilter implements Filter {


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		resp.setHeader("pragma","no-cache");
		resp.setHeader("cache-control","no-cache");
		resp.setHeader("expires","0");

		String url = req.getRequestURI();
		if (!url.contains("login") && !url.contains(".css") && !url.contains("images")
		&& !url.contains(".js") && !url.contains(".png") ){
			if (url.contains(".html") ) {
				
				if (req.getSession().getAttribute("user") == null) {
					resp.setContentType("text/html; charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.print("<script>alert('请先登录')</script>");
					out.print("<script>location.href='/MavenERP/login.html'</script>");
					out.flush();
					out.close();
				} else {
					chain.doFilter(req, resp);

				}
			} else {
				chain.doFilter(req, resp);
			}		
		}else {
			chain.doFilter(req, resp);
		}
		
	}

	@Override
	public void destroy() {

	}


}
