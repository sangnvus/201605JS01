package vn.edu.fu.veazy.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.response.Response;

public class VeazyAuthenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VeazyAuthenSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        Response resp = new Response(HttpServletResponse.SC_OK);
        response.flushBuffer();
        response.getWriter().flush();
        response.getWriter().print(resp.toResponseJson());
//        response.sendError(HttpServletResponse.SC_OK);
    }
}
