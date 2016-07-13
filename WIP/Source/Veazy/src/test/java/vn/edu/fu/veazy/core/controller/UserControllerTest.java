package vn.edu.fu.veazy.core.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import vn.edu.fu.veazy.core.common.Const;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:/WEB-INF/veazy-servlet.xml"})
public class UserControllerTest {
    
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SpringJUnit4ClassRunner.class);

    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    private MessageDigest digester;
    
    @Before
    public void setup() throws NoSuchAlgorithmException {
        this.mockMvc = webAppContextSetup(this.wac).build();
        digester = MessageDigest.getInstance("MD5");
    }
    
    @Test
    public void postRegister() throws Exception {
        this.mockMvc.perform(post(Const.URLMAPPING_REGISTER_PROCEED)
                .param("email", "myname@abc.com")
                .param("username", "user1")
                .param("password", encrypt("pw123@"))
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value("true"));
    }
    
    public String encrypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encrypt cannot be null or zero length");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            }
            else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }
}
