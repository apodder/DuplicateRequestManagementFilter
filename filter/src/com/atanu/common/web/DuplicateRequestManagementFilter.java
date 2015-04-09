package com.atanu.common.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class DuplicateRequestManagementFilter  implements Filter {

	private static ArrayList<String> requestTokens = new ArrayList<String>();
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(((HttpServletRequest)request).getMethod() == HttpMethod.POST.name()
				||
				((HttpServletRequest)request).getMethod() == HttpMethod.PUT.name())
		{
			CustomHttpServletRequestWrapper customHttpServletRequestWrapper
			= new CustomHttpServletRequestWrapper(request);
			String md5Hex = DigestUtils.md5Hex(customHttpServletRequestWrapper.getBody());
			if(!requestTokens.contains(md5Hex)){
				chain.doFilter(customHttpServletRequestWrapper, response);
				requestTokens.add(md5Hex);
				//int i = 0;
			}else{
				((HttpServletResponse) response).setStatus(HttpStatus.CONFLICT.value());
				return;
			}
		}
	}
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	public void destroy() {

	}

}
