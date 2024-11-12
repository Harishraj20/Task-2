package com.task.Repository;

import java.time.LocalDateTime;
import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task.Model.Login;
import com.task.Model.User;

@Repository
@Transactional
public class UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String addUserInfo(User user) {
        try {

            Session session = sessionFactory.getCurrentSession();
            session.save(user);
            return "User \"" + user.getUserName() + "\" Created Successfully!!";
        } catch (HibernateException e) {
            System.out.println(e);
            return "Corrupted";
        } catch (Exception e) {
            System.out.println("General error: " + e);
            return "GeneralException";
        }

    }

    public User checkUserByEmailid(String emailId) {

        try {

            Session session = sessionFactory.getCurrentSession();
            User user = session.createQuery("FROM User WHERE emailId = :emailId", User.class)
            .setParameter("emailId", emailId)
            .uniqueResult(); 
            if (user == null) {
                System.out.println("User dont exists");
                return null;
            } else {
                System.out.println("User exists");
                return user;
             

            }

        } catch (HibernateException e) {
            System.out.println(e);
            System.out.println("due to H exception");
            return null;
        } catch (Exception e) {
            System.out.println("General error: " + e);
            System.out.println("due to G exception");

            return null;
        }

    }

    public void updateFields(User user) {
        try {

            Session session = sessionFactory.getCurrentSession();
            user.setLoginStatus(user.getLoginStatus() + 1);
            session.update(user);

            Login userLog = new Login();
            userLog.setUser(user);
            userLog.setLoginInfo(LocalDateTime.now());
            session.save(userLog);
           
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("General error: " + e);
        }

      
    }

}