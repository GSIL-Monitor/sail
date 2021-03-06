package com.study.spring.sail.config.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义过滤器 filter
 *
 * @author 韩炜
 * @date 2019-01-15 11:00
 */
public class MyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        System.out.println("this is MyFilter,url :" + request.getRequestURI());
        chain.doFilter(srequest, sresponse);
    }

    @Override
    public void destroy() {

    }
}
