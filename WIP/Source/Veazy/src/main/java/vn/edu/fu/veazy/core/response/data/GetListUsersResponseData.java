/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response.data;

import java.util.ArrayList;
import java.util.List;
import vn.edu.fu.veazy.core.model.UserModel;

/**
 *
 * @author Hoang Linh
 */
public class GetListUsersResponseData {

    private List<GetUserResponseData> listUsers;

    public GetListUsersResponseData() {
        listUsers = new ArrayList<GetUserResponseData>();
    }

    public List<GetUserResponseData> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<GetUserResponseData> listUsers) {
        this.listUsers = listUsers;
    }

    public void addUser(GetUserResponseData data) {
        listUsers.add(data);
    }

}
