package com.task.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "register_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;
    private String password;
    private String emailId;
    private String dob;
    private String designation;
    private String role;
    private String gender;
    private int loginStatus = 0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Login> logins;

    public User() {
    }

    public User(String userName, String password, String emailId, String dob, String designation, String role,
            int loginStatus, String gender) {
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
        this.dob = dob;
        this.designation = designation;
        this.role = role;
        this.loginStatus = loginStatus;
    }

    public String getEmployeeId() {
        return "Emp00" + userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public List<Login> getLogins() {
        return logins;
    }

    public void setLogins(List<Login> logins) {
        this.logins = logins;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", emailId=" + emailId
                + ", dob=" + dob + ", designation=" + designation + ", role=" + role + ", gender=" + gender
                + ", loginStatus=" + loginStatus + "]";
    }

    // @Override
    // public String toString() {
    //     return "User{id=" + userId + ", name=" + userName + ", logins.size=" + (logins != null ? logins.size() : 0) + "}";
    // }
    
    
    

}


