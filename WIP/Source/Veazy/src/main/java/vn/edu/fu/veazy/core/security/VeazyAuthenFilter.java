package vn.edu.fu.veazy.core.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.controller.UserController;

public class VeazyAuthenFilter extends AbstractAuthenticationProcessingFilter {
    private Logger LOGGER = LoggerFactory.getLogger(VeazyAuthenFilter.class);
    
    private static final String URL_LOGIN = "/api/login";

    private String usernameParameter = "username";
    private String passwordParameter = "password";

    protected VeazyAuthenFilter() {
        super(URL_LOGIN);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        
        if (Const.CORS_HEADER_ENABLED) {
            addCorsHeader(response);
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
      LOGGER.debug(request.getMethod());
      LOGGER.debug(request.getQueryString());
      LOGGER.debug(request.getParameterMap() + "");
      LOGGER.debug(request.getReader().readLine() + "");
      
      Enumeration<String> headers = request.getHeaderNames();
      while (headers.hasMoreElements()) {
          String h = headers.nextElement();
          LOGGER.debug(h + ": " + request.getHeader(h));
      }
      if("POST".equalsIgnoreCase(request.getMethod())
              && (authentication == null || !authentication.isAuthenticated())
              && (request.getRequestURL().toString().endsWith(URL_LOGIN)
                      || request.getRequestURL().toString().endsWith(URL_LOGIN + "/"))) {
        super.doFilter(request, response, chain);
      } else {
        chain.doFilter(request, response);
      }
    }

    private void addCorsHeader(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3003");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Max-Age", "86400");
    }

}
