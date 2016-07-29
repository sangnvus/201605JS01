package vn.edu.fu.veazy.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;

public class VeazyAuthenFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        Response resp = new Response(ResponseCode.USERNAME_PASSWORD_NOT_MATCH);
        response.flushBuffer();
        response.getWriter().flush();
        response.getWriter().print(resp.toResponseJson());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
