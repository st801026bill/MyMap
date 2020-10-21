package com.bill.MyMap.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@SuppressWarnings("unchecked")
public class RequestParamsManagerFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		String reqUri = req.getRequestURI();
		
		try {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			chain.doFilter(request, httpServletResponse);
		} catch (Exception e) {
			request.setAttribute("Exception", e);
            request.getRequestDispatcher("/error/rethrow").forward(request, response);
		}
	}

}
