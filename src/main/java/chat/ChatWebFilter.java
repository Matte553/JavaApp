package chat;

import jakarta.faces.application.ResourceHandler;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Force browser to not store caches on request/response for all Faces.
 * Redirects to log in if session not set.
 */
@WebFilter(servletNames = "Faces Servlet")
public class ChatWebFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String resourcePath = request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER;
        //Exclude resources
        if (!request.getRequestURI().startsWith(resourcePath)) {
            response.setHeader("Cache-Control", "no-store, must-revalidate");
        }
        if (request.getRequestURI().endsWith("chat.xhtml")) {
            try {
                String customerNumber = (String) request.getSession().getAttribute("customerNumber");
                if (customerNumber == null) {
                    response.sendRedirect(request.getContextPath() + "/chat-login.xhtml");
                }
            } catch (IllegalStateException e) {
                System.out.println(e.toString());
            }
        }
        filterChain.doFilter(request, response);
    }
}
