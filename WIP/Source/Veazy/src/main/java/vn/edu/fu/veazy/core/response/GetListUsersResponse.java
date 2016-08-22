/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoang Linh
 */
public class GetListUsersResponse {

    private List<GetUserResponse> listUsers;

    public GetListUsersResponse() {
        listUsers = new ArrayList<GetUserResponse>();
    }

    public List<GetUserResponse> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<GetUserResponse> listUsers) {
        this.listUsers = listUsers;
    }

    public void addUser(GetUserResponse data) {
        listUsers.add(data);
    }

}
