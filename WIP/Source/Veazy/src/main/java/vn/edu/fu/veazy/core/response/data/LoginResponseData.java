/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response.data;

/**
 *
 * @author Hoang Linh
 */
public class LoginResponseData {

    private Integer role;

    public LoginResponseData() {
    }

    public LoginResponseData(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}
