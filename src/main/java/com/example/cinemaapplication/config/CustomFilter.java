package com.example.cinemaapplication.config;

import com.example.cinemaapplication.dto.user.LoggedUserDto;
import com.example.cinemaapplication.model.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

//        fast access
//        req.getSession().setAttribute("loggedUser", new LoggedUserDto(1, "a@a", UserRole.ADMIN));
//        filterChain.doFilter(req, resp);

        String requestUri = req.getRequestURI();
        LoggedUserDto loggedUser = (LoggedUserDto) req.getSession().getAttribute("loggedUser");
        if (
                requestUri.startsWith("/login") ||
                        requestUri.startsWith("/register") ||
                        requestUri.startsWith("/authFree") ||
                        requestUri.startsWith("/h2") ||
                        requestUri.endsWith(".css") ||
                        requestUri.endsWith(".jpg") ||
                        requestUri.endsWith(".png") ||
                        requestUri.endsWith(".woff2") ||
                        requestUri.endsWith(".ttf") ||
                        requestUri.endsWith(".ico") ||
                        requestUri.endsWith("app.js")
        ) {
            filterChain.doFilter(req, resp);
        } else if (loggedUser != null) {
            if (
                    requestUri.startsWith("/admin") && loggedUser.getRole() == UserRole.ADMIN ||
                            requestUri.startsWith("/user") && loggedUser.getRole() == UserRole.USER
            ) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
