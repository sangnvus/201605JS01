package vn.edu.fu.veazy.core.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.common.Utils;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.exception.PasswordIncorrectException;
import vn.edu.fu.veazy.core.form.RegisterForm;
import vn.edu.fu.veazy.core.form.UpdateUserForm;
import vn.edu.fu.veazy.core.model.UserModel;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private GenericDao<UserModel, Integer> userDao;

    @Override
    @Transactional
    public void saveUser(UserModel user) throws Exception {
        try {
            userDao.save(user);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void saveUser(RegisterForm userForm) throws Exception {
        try {
            String email = userForm.getEmail();
            String username = userForm.getUsername();
            String pw = userForm.getPassword();
            Long now = System.currentTimeMillis();
            UserModel user = new UserModel(email, username, pw, now);
            userDao.save(user);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }

    }

    @Override
    @Transactional
    public UserModel findUserById(Integer id) throws Exception {
        try {
            UserModel user = userDao.findById(id);
            if (user != null) {
                return user;
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public UserModel findUserByEmail(String email) throws Exception {
        try {
            UserModel u1 = new UserModel();
            u1.setEmail(email);
            List<UserModel> listSearchResult = userDao.findByExample(u1);
            if (listSearchResult != null && listSearchResult.size() > 0) {
                return listSearchResult.get(0);
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);

        }
        return null;
    }

    @Override
    @Transactional
    public UserModel findUserByUsername(String uname) throws Exception {
        try {
            UserModel u1 = new UserModel();
            u1.setUserName(uname);
            List<UserModel> listSearchResult = userDao.findByExample(u1);
            if (listSearchResult != null && listSearchResult.size() > 0) {
                return listSearchResult.get(0);
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);

        }
        return null;
    }

    @Override
    @Transactional
    public List<UserModel> findAllUser() throws Exception {
        try {
            List<UserModel> users = userDao.getAll();
            if (users != null && users.size() > 0) {
                return users;
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public void update(UserModel user) throws Exception {
        try {
            userDao.update(user);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void update(UserModel user, UpdateUserForm form) throws Exception {
        String fName = form.getFirstName();
        String lName = form.getLastName();
        Long dob = form.getDob();
        String addr = form.getAddress();
        String hobby = form.getHobby();
        String bio = form.getBio();
        String website = form.getWebsite();
        
        if (!Utils.isNullOrEmpty(fName)) {
            user.setFirstName(fName);
        }
        
        if (!Utils.isNullOrEmpty(lName)) {
            user.setLastName(lName);
        }
        
        if (dob != null && dob > 0) {
            user.setDob(dob);
        }
        
        if (!Utils.isNullOrEmpty(addr)) {
            user.setAddress(addr);
        }
        
        if (!Utils.isNullOrEmpty(hobby)) {
            user.setHobby(hobby);
        }
        
        if (!Utils.isNullOrEmpty(bio)) {
            user.setHobby(bio);
        }
        
        if (!Utils.isNullOrEmpty(website)) {
            user.setHobby(website);
        }
        
        update(user);
    }

    @Override
    @Transactional
    public void delete(UserModel user) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional
    public void deleteUsers(List<UserModel> users) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional
    public int size() throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

	@Override
    @Transactional
	public void changeUserRoll(Integer userId, int role) throws Exception {
		UserModel user = findUserById(userId);
		user.setRole(role);
		userDao.update(user);
	}

    @Override
    @Transactional
    public void changePassword(Integer id, String oldPassword, String newPassword) throws Exception {
        UserModel user = new UserModel();
        user.setId(id);
        user.setEncryptedPassword(oldPassword);
        List<UserModel> listMatch = userDao.findByExample(user);
        if (listMatch == null || listMatch.size() != 1) {
            throw new PasswordIncorrectException("Wrong old password");
        }
        user = listMatch.get(0);
        user.setEncryptedPassword(newPassword);
        userDao.update(user);
    }
}
