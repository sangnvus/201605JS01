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
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.form.UpdateUserForm;
import vn.edu.fu.veazy.core.model.UserModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private GenericDao<UserModel, Integer> userDao;
    
    private UserModel initUser;
    private String initEmail = "init@email.com";
    private String initUsername = "init";
    private String initPassword = "123456";

    @Before
    public void setUp() throws Exception {
        initUser = new UserModel(initEmail, initUsername, initPassword,
                System.currentTimeMillis());
        userDao.save(initUser);
    }

    @After
    public void tearDown() {
        try {
            if (initUser != null) userDao.delete(initUser);
            List<UserModel> listUser = userDao.getAll();
            if (listUser != null && listUser.size() > 0) {
                for (UserModel m : listUser) {
                    userDao.delete(m);
                }
            }
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testSaveUser() throws Exception {
        UserModel user = new UserModel("newone@gmail.com", "newone", "123456",
                System.currentTimeMillis());
        userDao.save(user);
        Assert.assertNotNull(user.getId());
    }

    @Test(expected = Exception.class)
    public void testSaveUserNull() throws Exception {
        UserModel m = null;
        userService.saveUser(m);
    }

    @Test
    public void testFindUserById() throws Exception {
        UserModel user = userService.findUserById(initUser.getId());
        Assert.assertNotNull(user);
    }

    @Test
    public void testFindUserById2() throws Exception {
        UserModel user = userService.findUserById(-1);
        Assert.assertNull(user);
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        UserModel user = userService.findUserByEmail(initUser.getEmail());
        Assert.assertNotNull(user);
    }

    @Test
    public void testFindUserByEmail4() throws Exception {
        UserModel user = userService.findUserByEmail("sdfhishfywgfsdk223423423");
        Assert.assertNull(user);
    }

    @Test
    public void testFindUserByUserName() throws Exception {
        UserModel user = userService.findUserByUsername(initUser.getUserName());
        Assert.assertNotNull(user);
    }

    @Test
    public void testFindUserByUserName4() throws Exception {
        UserModel user = userService.findUserByUsername("skjdhfkhsdfsfw43u48495");
        Assert.assertNull(user);
    }

    @Test
    public void testFindAllUser() throws Exception {
        List<UserModel> findAllUser = userService.findAllUser();
        Assert.assertNotNull(findAllUser);
        Assert.assertTrue(findAllUser.size() > 0);
    }

    @Test
    public void testUpdateUser() throws Exception {
        UpdateUserForm form = new UpdateUserForm();
        String address = "FU Hoa Lac";
        form.setAddress(address);
        userService.update(initUser, form);
        Assert.assertEquals(address, initUser.getAddress());
    }

    @Test
    public void testChangeUserRole() throws Exception {
        Integer role = Const.ROLE_EDITOR;
        userService.changeUserRoll(initUser.getId(), role);
        Assert.assertEquals(role, initUser.getRole());
    }

    @Test
    public void testChangePassword() throws Exception {
        String newPass = "new123";
        userService.changePassword(initUser.getId(), initUser.getEncryptedPassword(), newPass);
        Assert.assertEquals(newPass, initUser.getEncryptedPassword());
    }
}