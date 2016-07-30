/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Hoang Linh
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`User`")
public class UserModel extends BasicModel {
    
    @Column(name = "email", columnDefinition="VARCHAR(254) UNIQUE", nullable = false)
    private String email;
    @Column(name = "userName", columnDefinition="VARCHAR(30) UNIQUE", nullable = false)
    private String userName;
    @Column(name = "password", columnDefinition="CHAR(32)", nullable = false)
    private String encryptedPassword;
    @Column(name = "firstName", columnDefinition="VARCHAR(30)", nullable = true)
    private String firstName;
    @Column(name = "lastName", columnDefinition="VARCHAR(30)", nullable = true)
    private String lastName;
    @Column(name = "dob", nullable = true)
    private Long dob;
    @Column(name = "joinDate", nullable = false)
    private Long joinDate;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "role", columnDefinition="INT DEFAULT 3", nullable = false)
    private Integer role;
    @Column(name = "hobby", nullable = true)
    private String hobby;
    @Column(name = "bio", nullable = true)
    private String bio;
    @Column(name = "website", nullable = true)
    private String website;
    @Column(name = "isBanned", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = false)
    private Boolean isBanned = false;

    public UserModel() {
    }

    public UserModel(String email, String userName, String encryptedPassword, Long joinDate) {
        super();
        this.email = email;
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
        this.joinDate = joinDate;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

}
