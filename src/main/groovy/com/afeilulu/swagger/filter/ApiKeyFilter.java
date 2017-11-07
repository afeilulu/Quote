package com.afeilulu.swagger.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class ApiKeyFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String key = req.getParameter("api_key");
//        System.out.println("Api key value is: " + key);
//        System.out.println(req.getParameterMap());

        WrappedRequest wrappedRequest = new WrappedRequest((HttpServletRequest) req);
        // do not add authorization when anonymous access
        if (!((HttpServletRequest) req).getRequestURI().contains("/rest/public")) {
            // do not add header while having authorization already
            String bearer = wrappedRequest.getHeader("Authorization");
            if (bearer == null || bearer.length() == 0) {
                wrappedRequest.addHeader("Authorization", "Bearer " + key);
            }
        }
        chain.doFilter(wrappedRequest, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}
