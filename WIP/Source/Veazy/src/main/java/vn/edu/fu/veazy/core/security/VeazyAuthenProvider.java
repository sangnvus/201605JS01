package vn.edu.fu.veazy.core.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.model.UserModel;

@Component
@Transactional
public class VeazyAuthenProvider implements AuthenticationProvider, UserDetailsService {
    
    private Logger LOGGER = LoggerFactory.getLogger(VeazyAuthenProvider.class);
    
    @Autowired
    private GenericDao<UserModel, Integer> userDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString().trim();
        
        LOGGER.debug("<" + username + ">");
        LOGGER.debug("<" + password + ">");
        
        User theOneThatGotAway = (User) loadUserByUsername(username);
        LOGGER.debug(theOneThatGotAway + "");
        if (theOneThatGotAway != null) LOGGER.debug("<" + theOneThatGotAway.getPassword() + ">");
        if (theOneThatGotAway == null || !password.equals(theOneThatGotAway.getPassword().trim())) {
            return null;
        }
        
        return new UsernamePasswordAuthenticationToken(username, password, theOneThatGotAway.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel sample = new UserModel();
        sample.setUserName(username);
        List<UserModel> listMatchUsers = null;
        try {
            listMatchUsers = userDao.findByExample(sample);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (listMatchUsers == null || listMatchUsers.size() != 1) {
            // FIXME custom exception
//            throw new IllegalArgumentException("Username not found");
            return null;
        }
        UserModel onlyOne = listMatchUsers.get(0);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(onlyOne.getRole())));
        return new User(onlyOne.getUserName(), onlyOne.getEncryptedPassword(),
                true, true, true, !onlyOne.isIsBanned(), authorities);
    }

}
