/**
 * 
 */
package com.cl.elena.routerZuul.filter;

import org.springframework.context.annotation.Configuration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author charlie
 *
 */
@Configuration
public class CustomizeErrorFilter extends ZuulFilter {

	/**
	 * 
	 */
	public CustomizeErrorFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		return "出错了，没有你想要的！！";
	}

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
