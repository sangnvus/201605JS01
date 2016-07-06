package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.model.UserModel;

public interface UserService {
    void saveUser(UserModel user) throws Exception;
    public UserModel findUserById(Integer id) throws Exception;
    public UserModel findUserByEmail(String email) throws Exception;
    public List<UserModel> findAllUser() throws Exception;
    public void update(UserModel user) throws Exception;
    public void delete(UserModel user) throws Exception;
    public void deleteUsers(List<UserModel> users) throws Exception;
    public int size() throws Exception;
}