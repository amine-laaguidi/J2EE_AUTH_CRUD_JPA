package web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns ={"/login","/logout","/register","/students"})
public class MyFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse httpres = (HttpServletResponse) response;
        httpres.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        httpres.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        httpres.setDateHeader("Expires", 0); // Proxies.
        chain.doFilter(request, response);
    }
}
