/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.exception.PasswordIncorrectException;
import vn.edu.fu.veazy.core.form.RegisterForm;
import vn.edu.fu.veazy.core.form.UpdateUserForm;
import vn.edu.fu.veazy.core.model.UserModel;

/**
 *
 * @author Hoang Linh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class UserServiceTest {

    private UserModel user;
    @Autowired
    private UserService userService;
    private Integer userId;
    private String email;
    private String username;
    private String password;
    private Integer role;
    private UpdateUserForm form;

    public UserModel setUpUser() throws Exception {
        RegisterForm form = new RegisterForm();
        form.setUsername(username);
        form.setEmail(email);
        form.setPassword(password);
        userService.saveUser(form);
        UserModel user = userService.findUserByUsername(username);
        return user;
    }

    @Before
    public void setUp() throws Exception {
        username = "user1";
        email = "user1@email.com";
        password = "password";
        user = setUpUser();
        userId = user.getId();
        form = new UpdateUserForm("first", "last", (long) 1234, email, email, email, email, email);
        role = Const.ROLE_EDITOR;
    }

    @After
    public void tearDown() {
        try {
            userService.delete(user);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testSaveUser() throws Exception {
        userService.saveUser(user);
    }

    @Test
    public void testSaveUser2() throws Exception {
        RegisterForm form = new RegisterForm();
        form.setUsername("user2");
        form.setEmail("user2@email.com");
        form.setPassword("user2");
        userService.saveUser(form);
    }

    @Test
    public void testFindUserById() throws Exception {
        UserModel user = userService.findUserById(userId);
        Assert.assertNotNull(user);
    }

    @Test
    public void testFindUserById2() throws Exception {
        UserModel user = userService.findUserById(-1);
        Assert.assertNull(user);
    }

    @Test
    public void testFindUserById3() throws Exception {
        UserModel user = userService.findUserById(null);
        Assert.assertNull(user);
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        UserModel user = userService.findUserByEmail(email);
        Assert.assertNotNull(user);
    }

    @Test
    public void testFindUserByEmail2() throws Exception {
        UserModel user = userService.findUserByEmail(null);
        Assert.assertNull(user);
    }

    @Test
    public void testFindUserByEmail3() throws Exception {
        UserModel user = userService.findUserByEmail("");
        Assert.assertNull(user);
    }

    @Test
    public void testFindUserByEmail4() throws Exception {
        UserModel user = userService.findUserByEmail("notexistemail.com");
        Assert.assertNull(user);
    }

    @Test
    public void testFindUserByUserName() throws Exception {
        UserModel user = userService.findUserByUsername(username);
        Assert.assertNotNull(user);
    }

    @Test
    public void testFindUserByUserName2() throws Exception {
        UserModel user = userService.findUserByUsername(null);
        Assert.assertNull(user);
    }

    @Test
    public void testFindUserByUserName3() throws Exception {
        UserModel user = userService.findUserByUsername("");
        Assert.assertNull(user);
    }

    @Test
    public void testFindUserByUserName4() throws Exception {
        UserModel user = userService.findUserByUsername("notExistUserName");
        Assert.assertNull(user);
    }

    @Test
    public void testFindAllUser() throws Exception {
        List<UserModel> findAllUser = userService.findAllUser();
        Assert.assertNotNull(findAllUser);
        Assert.assertNotEquals(0, findAllUser.size());
    }

    @Test
    public void testUpdateUser() throws Exception {
        userService.update(user, form);
    }

    @Test
    public void testUpdateUser2() throws Exception {
        userService.update(user);
    }

    @Test
    public void testUpdateUser3() throws Exception {
        try {
            userService.update(null, form);
        } catch (Exception ex) {
            Assert.assertEquals(new Exception(), ex);
        }
    }

    @Test
    public void testUpdateUser4() throws Exception {
        try {
            userService.update(null, null);
        } catch (Exception ex) {
            Assert.assertEquals(new Exception(), ex);
        }
    }

    @Test
    public void testChangeUserRole() throws Exception {
        userService.changeUserRoll(userId, role);
    }

    @Test
    public void testChangePassword() throws Exception {
        userService.changePassword(userId, password, "newPassword");
    }

    @Test
    public void testChangePassword2() throws Exception {
        try {
            userService.changePassword(-1, "wrongPassword", "newPassword");
        } catch (Exception ex) {
            Assert.assertEquals(new PasswordIncorrectException("Wrong old password"), ex);
        }
    }

    @Test
    public void testChangePassword3() throws Exception {
        try {
            userService.changePassword(userId, "wrongPassword", "newPassword");
        } catch (Exception ex) {
            Assert.assertEquals(new PasswordIncorrectException("Wrong old password"), ex);
        }
    }
}
