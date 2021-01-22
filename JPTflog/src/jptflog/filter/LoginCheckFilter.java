package jptflog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jptflog.service.Action;

public class LoginCheckFilter implements Filter, Action{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		boolean bool = false;
		if(session != null) {
			if(session.getAttribute("admin") != null) {
				bool = true;
				}		
			}
		if(!bool) {
			chain.doFilter(request, response);
		}else {			
			String cmd = request.getParameter("cmd");
			Action action = null;
			
			if(action != null) {
				action.execute((HttpServletRequest)request, (HttpServletResponse)response);
			}
			
		}	
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
