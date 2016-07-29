package vn.edu.fu.veazy.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import vn.edu.fu.veazy.core.common.utils.HttpUtils;
import vn.edu.fu.veazy.core.response.Response;

public class Veazy403Handler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Response resp = new Response(HttpServletResponse.SC_FORBIDDEN);
        HttpUtils.addCorsHeader(response);
        response.flushBuffer();
        response.getWriter().flush();
        response.getWriter().print(resp.toResponseJson());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        
    }


}
