package com.task.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    

}