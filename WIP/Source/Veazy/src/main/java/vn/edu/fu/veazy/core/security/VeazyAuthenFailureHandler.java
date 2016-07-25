package vn.edu.fu.veazy.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.response.Response;

public class VeazyAuthenFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VeazyAuthenFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        Response resp = new Response(HttpServletResponse.SC_UNAUTHORIZED);
        response.flushBuffer();
        response.getWriter().flush();
        response.getWriter().print(resp.toResponseJson());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
