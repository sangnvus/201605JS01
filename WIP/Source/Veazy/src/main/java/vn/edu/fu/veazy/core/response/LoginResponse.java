/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response;

/**
 *
 * @author Hoang Linh
 */
public class LoginResponse {

    private Integer role;

    public LoginResponse() {
    }

    public LoginResponse(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}
