package com.debamalya.routine;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter extends GenericFilterBean implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods","*");
		httpServletResponse.setHeader("Access-Control-Allow-Headers","*");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials","false");
		System.out.println("Cors filter ends here");
		chain.doFilter(request, response);	
	}
}
