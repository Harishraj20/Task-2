package com.task.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.task.Model.Login;
import com.task.Model.User;
import com.task.Repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public String addUsers(User user) {
        if (repo.checkUserByEmailid(user.getEmailId()) != null) {
            return "User already exists with this email!";
        }
        return repo.addUserInfo(user);
    }

    public User checkUserByMailId(String emailId) {
        return repo.checkUserByEmailid(emailId);
    }

    public void updateCredentials(User user) {
        user.setLoginStatus(user.getLoginStatus() + 1);
        repo.updateFields(user);

        Login userLog = new Login();
        userLog.setUser(user);
        userLog.setLoginInfo(LocalDateTime.now());
        repo.saveLoginInfo(userLog);
    }

    public List<User> fetchAllUsers() {
        return repo.fetchUsers();
    }

    public List<Login> getLoginInfo(int userId) {
        return repo.getLoginById(userId);
    }

    public boolean deleteUserById(int userId) {
        User user = repo.findUser(userId);
        if (user != null) {
            repo.deleteUser(userId);
            return true;
        }
        return false;
    }

    public User findUserById(int userIdForAction) {
        return repo.findUser(userIdForAction);
    }

    public String updateUsers(User updateUser, int paramId) {
        User existingUserWithEmail = repo.findUserByEmailExcludingId(updateUser.getEmailId(), paramId);
        if (existingUserWithEmail != null) {
            return "Email is already associated with another user!";
        }

        User existingUser = repo.findUser(paramId);
        if (existingUser != null) {
            existingUser.setUserName(updateUser.getUserName());
            existingUser.setDesignation(updateUser.getDesignation());
            existingUser.setEmailId(updateUser.getEmailId());
            existingUser.setDob(updateUser.getDob());
            existingUser.setPassword(updateUser.getPassword());
            existingUser.setGender(updateUser.getGender());
            existingUser.setRole(updateUser.getRole());

            repo.updateUser(existingUser);
            return "User updated successfully!";
        }
        return "User not found!";
    }
}
