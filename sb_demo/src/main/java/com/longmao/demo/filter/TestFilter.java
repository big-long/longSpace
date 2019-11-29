package com.longmao.demo.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter(value = "/TestFilter", urlPatterns = "/**")
public class TestFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpreRequest = (HttpServletRequest) request;
		httpreRequest.setCharacterEncoding("UTF-8");
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpreRequest) {
			
		

			@Override
			public String getParameter(String name) {
				String value = httpreRequest.getParameter(name);
				if(value!=null) {
					value=value.replace("fuck", "*****");
					return value;
				}
				return super.getParameter(name);
			}

			@Override
			public String[] getParameterValues(String name) {
				String[] values = httpreRequest.getParameterValues(name);
				if(values!=null&&values.length>0) {
					values[0]=values[0].replace("fuck", "*********");
					return values;
				}
				return super.getParameterValues(name);
			}

		};

		chain.doFilter(wrapper, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
