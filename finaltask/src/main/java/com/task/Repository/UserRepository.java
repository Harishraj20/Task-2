package com.task.Repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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

    public List<User> fetchUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = null;

        try {
            Criteria criteria = session.createCriteria(User.class);
            users = criteria.list();
            // System.out.println(users);
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("General error: " + e);
            return null;
        }
        return users;
    }

    public List<Login> getLoginById(int userId) {

        Session session = sessionFactory.getCurrentSession();
        List<Login> logins = null;

        try {

            String hql = "FROM User u LEFT JOIN FETCH u.logins WHERE u.userId = :userId";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("userId", userId);

            User user = query.uniqueResult();
            logins = user.getLogins();

        } catch (HibernateException e) {
            System.out.println("Hibernate exception:" + e);
        } catch (Exception e) {
            System.out.println("Run Time exception:" + e);

        }

        return logins;

    }

    public boolean deleteUser(int user_id) {
        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("into try block");
            User user = session.get(User.class, user_id);
            System.out.println(user);
            session.delete(user);
            System.out.println("deleetd successfully");

            return true;

        } catch (HibernateException e) {
            System.out.println("Hibernate exception:" + e);
            return false;
        } catch (Exception e) {
            System.out.println("Run Time exception:" + e);
            return false;

        }

    }

    public User findUser(int userIdForAction) {
        Session session = sessionFactory.getCurrentSession();
        User user = null;

        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("userId", userIdForAction));
            user = (User) criteria.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("General error: " + e);
            return null;
        }
        return user;

    }

    public String updateUserInfo(User updateUser) {
        Session session = sessionFactory.getCurrentSession();
        String msg = "MailId already exists!!!";

        try {
            Criteria criteria = session.createCriteria(User.class);

            criteria.add(Restrictions.eq("emailId", updateUser.getEmailId()));
            criteria.add(Restrictions.ne("userId", updateUser.getUserId()));

            User existingUserWithEmail = (User) criteria.uniqueResult();

            if (existingUserWithEmail != null) {
                return msg;
            }

            criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("userId", updateUser.getUserId()));
            User existingUser = (User) criteria.uniqueResult();

            if (existingUser != null) {
                existingUser.setUserName(updateUser.getUserName());
                existingUser.setDesignation(updateUser.getDesignation());
                existingUser.setEmailId(updateUser.getEmailId());
                existingUser.setDob(updateUser.getDob());
                existingUser.setPassword(updateUser.getPassword());
                existingUser.setGender(updateUser.getGender());
                existingUser.setRole(updateUser.getRole());

                session.update(existingUser);
                return "User updated successfully!";
            }
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("General error: " + e);
        }
        return null;
    }

}