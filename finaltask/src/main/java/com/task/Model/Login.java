package com.task.Model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "login_info")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int logId;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime loginInfo;

    public Login() {
    }

    public Login(User user, int visitCount, LocalDateTime loginInfo) {
        this.user = user;
        this.loginInfo = loginInfo;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LocalDateTime loginInfo) {
        this.loginInfo = loginInfo;
    }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return loginInfo.format(formatter);

    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return loginInfo.format(formatter);

    }
}
