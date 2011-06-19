package edu.ecm.blog.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GuardFilter implements Filter  {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	         FilterChain chain) throws IOException, ServletException {
	   try {
	      chain.doFilter(request, response);
	   } catch (Exception e) {
	      response.getWriter().write("got you !");
	   }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	@Override
	public void destroy() {
		
	}
}
