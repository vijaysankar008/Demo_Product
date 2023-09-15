package com.nandha.Nandha.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nandha.Nandha.advice.InvalidHeadersFileException;

import io.micrometer.common.lang.Nullable;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class RequestInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object object)throws InvalidHeadersFileException {
		if(StringUtils.isBlank(request.getHeader("abcd")))
			throw new InvalidHeadersFileException();
		System.out.println("Pre Handle is Calling");
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		System.out.println("PostHandling is calling");
	}
	@Override
	 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		System.out.println("After Completion is calling");
	}
}
