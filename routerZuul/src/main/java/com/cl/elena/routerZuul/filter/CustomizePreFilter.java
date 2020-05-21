/**
 * 
 */
package com.cl.elena.routerZuul.filter;

import org.springframework.context.annotation.Configuration;

import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author charlie
 *
 */
@Configuration
public class CustomizePreFilter extends ZuulFilter {

	/**
	 * 
	 */
	public CustomizePreFilter() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.setDebugRouting(true);
		ctx.setDebugRequest(true);
		System.out.println("router host:" + ctx.getRouteHost());
		System.out.println("CustomizePreFilter" + "run() method!!");
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
