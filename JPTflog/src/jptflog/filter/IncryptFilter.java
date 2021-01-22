package jptflog.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import jptflog.filter.LoginWrapper;

@WebFilter(urlPatterns= {"/user", "/password"})
public class IncryptFilter implements Filter {

    public IncryptFilter() {
        
    }

    public void destroy() {
       
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // ServletRequest - 부모 / HttpServletRequest - 자식 관계
        HttpServletRequest hRequest = (HttpServletRequest)request;
       
        LoginWrapper lw = new LoginWrapper(hRequest);
            
        chain.doFilter(lw, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
       
    }

}

