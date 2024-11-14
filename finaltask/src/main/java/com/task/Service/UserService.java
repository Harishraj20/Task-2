package com.task.Service;

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

        String forw = repo.addUserInfo(user);

        return forw;
       
    }

    public User checkUserByMailId(String emailId) {
       


        return repo.checkUserByEmailid(emailId);
    }

    public void updateCredentials(User user) {
       repo.updateFields(user);
    }

    public List<User> fetchAllUsers() {
        return repo.fetchUsers();
    }

    public List<Login> getLoginInfo(int userId) {
       return repo.getLoginById(userId);
    }

    public boolean  deleteUserById(int user_id) {
       boolean result = repo.deleteUser(user_id);


       return result;






    }

    public User findUserById(int userIdForAction) {
      return repo.findUser(userIdForAction);
    }

    public String updateUsers(User updateUser) {
      return repo.updateUserInfo(updateUser);
    }



    

}