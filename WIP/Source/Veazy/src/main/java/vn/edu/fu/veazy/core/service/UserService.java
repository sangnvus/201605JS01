package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.form.RegisterForm;
import vn.edu.fu.veazy.core.form.UpdateUserForm;
import vn.edu.fu.veazy.core.model.UserModel;

public interface UserService {
    public void saveUser(UserModel user) throws Exception;
    public void saveUser(RegisterForm user) throws Exception;
    public UserModel findUserById(Integer id) throws Exception;
    public UserModel findUserByUsername(String email) throws Exception;
    public UserModel findUserByEmail(String email) throws Exception;
    public List<UserModel> findAllUser() throws Exception;
    public void update(UserModel user) throws Exception;
    public void update(UserModel user, UpdateUserForm form) throws Exception;
    public void delete(UserModel user) throws Exception;
    public void deleteUsers(List<UserModel> users) throws Exception;
    public int size() throws Exception;
    public void changeUserRoll(Integer userId, int role) throws Exception;
}