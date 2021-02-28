package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/index.jsp")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.endsWith("/auth")) {
            System.out.println("doFilter 20 endsWith(auth)");
            chain.doFilter(request, response);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            System.out.println("doFilter 25 .getAttribute(\"user\") == null");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        System.out.println("doFilter 25 End");
        chain.doFilter(request, response);
    }
}

