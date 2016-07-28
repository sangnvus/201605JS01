package vn.edu.fu.veazy.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.response.Response;

public class Veazy403Handler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Response resp = new Response(HttpServletResponse.SC_FORBIDDEN);
        if (Const.CORS_HEADER_ENABLED) {
            addCorsHeader(response);
        }
        response.flushBuffer();
        response.getWriter().flush();
        response.getWriter().print(resp.toResponseJson());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        
    }

    private void addCorsHeader(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3003");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Max-Age", "86400");
    }

}
