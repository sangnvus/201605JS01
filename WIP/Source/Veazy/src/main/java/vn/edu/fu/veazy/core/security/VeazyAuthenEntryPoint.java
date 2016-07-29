package vn.edu.fu.veazy.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import vn.edu.fu.veazy.core.common.utils.HttpUtils;
import vn.edu.fu.veazy.core.response.Response;

public class VeazyAuthenEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        Response resp = new Response(HttpServletResponse.SC_UNAUTHORIZED);
        HttpUtils.addCorsHeader(response);
        response.flushBuffer();
        response.getWriter().flush();
        response.getWriter().print(resp.toResponseJson());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        
    }

}
