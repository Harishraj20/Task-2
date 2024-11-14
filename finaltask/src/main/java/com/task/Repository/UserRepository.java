package com.task.Repository;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
            return "User \"" + user.getUserName() + "\" Created Successfully!";
        } catch (HibernateException e) {
            System.out.println("HibernateException in addUserInfo: " + e);
            return "Error saving user due to HibernateException";
        } catch (Exception e) {
            System.out.println("Exception in addUserInfo: " + e);
            return "Error saving user due to general exception";
        }
    }

    public User checkUserByEmailid(String emailId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM User WHERE emailId = :emailId", User.class)
                    .setParameter("emailId", emailId)
                    .uniqueResult();
        } catch (HibernateException e) {
            System.out.println("HibernateException in checkUserByEmailid: " + e);
            return null;
        } catch (Exception e) {
            System.out.println("Exception in checkUserByEmailid: " + e);
            return null;
        }
    }

    public void updateFields(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(user);
        } catch (HibernateException e) {
            System.out.println("HibernateException in updateFields: " + e);
        } catch (Exception e) {
            System.out.println("Exception in updateFields: " + e);
        }
    }

    public void saveLoginInfo(Login loginInfo) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(loginInfo);
        } catch (HibernateException e) {
            System.out.println("HibernateException in saveLoginInfo: " + e);
        } catch (Exception e) {
            System.out.println("Exception in saveLoginInfo: " + e);
        }
    }

    public List<User> fetchUsers() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(User.class);
            return criteria.list();
        } catch (HibernateException e) {
            System.out.println("HibernateException in fetchUsers: " + e);
            return null;
        } catch (Exception e) {
            System.out.println("Exception in fetchUsers: " + e);
            return null;
        }
    }

    public List<Login> getLoginById(int userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            User user = session.createQuery("FROM User u LEFT JOIN FETCH u.logins WHERE u.userId = :userId", User.class)
                    .setParameter("userId", userId)
                    .uniqueResult();
            return user != null ? user.getLogins() : null;
        } catch (HibernateException e) {
            System.out.println("HibernateException in getLoginById: " + e);
            return null;
        } catch (Exception e) {
            System.out.println("Exception in getLoginById: " + e);
            return null;
        }
    }

    public void deleteUser(int userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
            }
        } catch (HibernateException e) {
            System.out.println("HibernateException in deleteUser: " + e);
        } catch (Exception e) {
            System.out.println("Exception in deleteUser: " + e);
        }
    }

    public User findUser(int userIdForAction) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(User.class, userIdForAction);
        } catch (HibernateException e) {
            System.out.println("HibernateException in findUser: " + e);
            return null;
        } catch (Exception e) {
            System.out.println("Exception in findUser: " + e);
            return null;
        }
    }

    public User findUserByEmailExcludingId(String emailId, int userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("emailId", emailId))
                    .add(Restrictions.ne("userId", userId));
            return (User) criteria.uniqueResult();
        } catch (HibernateException e) {
            System.out.println("HibernateException in findUserByEmailExcludingId: " + e);
            return null;
        } catch (Exception e) {
            System.out.println("Exception in findUserByEmailExcludingId: " + e);
            return null;
        }
    }

    public void updateUser(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(user);
        } catch (HibernateException e) {
            System.out.println("HibernateException in updateUser: " + e);
        } catch (Exception e) {
            System.out.println("Exception in updateUser: " + e);
        }
    }
}
