package vn.edu.fu.veazy.core.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import vn.edu.fu.veazy.core.response.LoginResponse;
import vn.edu.fu.veazy.core.response.Response;

public class VeazyAuthenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @SuppressWarnings("unchecked")
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        Response resp = new Response(HttpServletResponse.SC_OK);
        List<GrantedAuthority> roles = (List<GrantedAuthority>) authentication.getAuthorities();
        if (roles != null && roles.size() > 0) {
            GrantedAuthority aRole = roles.get(0);
            resp.setData(new LoginResponse(Integer.valueOf(aRole.getAuthority())));
        }
        response.flushBuffer();
        response.getWriter().flush();
        response.getWriter().print(resp.toResponseJson());
//        response.sendError(HttpServletResponse.SC_OK);
    }
}
