package vn.edu.fu.veazy.core.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class VeazyAuthenFilter extends AbstractAuthenticationProcessingFilter {
    
    private static final String URL_LOGIN = "/api/login";

    private String usernameParameter = "username";
    private String passwordParameter = "password";

    protected VeazyAuthenFilter() {
        super(URL_LOGIN);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = request.getParameter(usernameParameter);
        String password = request.getParameter(passwordParameter);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
      FilterChain chain) throws IOException, ServletException {
      final HttpServletRequest request = (HttpServletRequest) req;
      final HttpServletResponse response = (HttpServletResponse) res;
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if(request.getMethod().equals("POST")
              && request.getRequestURL().toString().endsWith(URL_LOGIN)
              && (authentication == null || !authentication.isAuthenticated())) {
        super.doFilter(request, response, chain);
      } else {
        chain.doFilter(request, response);
      }
    }

}
