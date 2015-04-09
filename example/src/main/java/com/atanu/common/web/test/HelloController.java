package com.atanu.common.web.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping(value = "sayhello", method = RequestMethod.GET)
	public @ResponseBody String sayHello(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		return "Hello";
	}
	
	@RequestMapping(value = "sayhello", method = RequestMethod.POST)
	public @ResponseBody String postHello(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		//int i = 10 /0;
		return "Hello";
	}
	
	@RequestMapping(value = "sayhello", method = RequestMethod.PUT)
	public @ResponseBody String putHello(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		return "Hello";
	}
	
	@RequestMapping(value = "sayhello", method = RequestMethod.DELETE)
	public @ResponseBody String deleteHello(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		return "Hello";
	}
	
}
