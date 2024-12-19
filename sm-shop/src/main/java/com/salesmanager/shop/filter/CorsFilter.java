package com.salesmanager.shop.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CorsFilter implements HandlerInterceptor {

	private static final String ALLOWED_ORIGINS = "http://pesco.iptime.org:8082";
	private static final String ALLOWED_METHODS = "GET, POST, PUT, DELETE";
	private static final String ALLOWED_HEADERS = "X-Auth-Token, Content-Type, Authorization, Cache-Control, X-Requested-With";

	public CorsFilter() {}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String origin = request.getHeader("Origin");
		if (origin == null || !ALLOWED_ORIGINS.equals(origin)) {
			origin = "*";
		}

		httpResponse.setHeader("Access-Control-Allow-Origin", origin);
		httpResponse.setHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);
		httpResponse.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			httpResponse.setStatus(HttpServletResponse.SC_OK);
			return false;
		}

		return true;
	}
}
