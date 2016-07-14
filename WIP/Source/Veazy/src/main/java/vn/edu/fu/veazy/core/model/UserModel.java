/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

/**
 *
 * @author Hoang Linh
 */
public class UserModel extends BasicModel {

    private String email;
    private String userName;
    private String encryptedPassword;
    private String firstName;
    private String lastName;
    private Long dob;
    private Long joinDate;
    private String address;
    private Integer role;
    private String hobby;
    private Boolean isBanned;

    public UserModel() {
    }

    public UserModel(String email, String userName, String encryptedPassword) {
        super();
        this.email = email;
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
    }

    public UserModel(String email, String userName, String encryptedPassword, String firstName, String lastName,
            Long dob, Long joinDate, String address, Integer role, String hobby, Boolean isBanned) {
        super();
        this.email = email;
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.joinDate = joinDate;
        this.address = address;
        this.role = role;
        this.hobby = hobby;
        this.isBanned = isBanned;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDob() {
        return dob;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }

    public Long getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Long joinDate) {
        this.joinDate = joinDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public boolean isIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Boolean isBanned) {
        this.isBanned = isBanned;
    }

}
