package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.entity.User;
import com.util.HibernateUtil;

public class UserDao {
	
	
	
	public User register(User user){
	    Session session=HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    session.save(user);
	    session.getTransaction().commit();
	    session.close();
		User resultUser=findByUserId(user.getUserId());
	    return resultUser;
	}
		
	
	public User checkUserName(String userName){
	    Session session=HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Query query=session.createQuery("from User where userName=:userName");
		query.setString("userName", userName);
		User resultUser=(User)query.uniqueResult();
	    session.getTransaction().commit();
	    session.close();
	    return resultUser;
	}
	
	public User update(User user){
	    Session session=HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    session.merge(user);
	    session.getTransaction().commit();
	    session.close();
		User resultUser=findByUserId(user.getUserId());
	    return resultUser;
	}

	public List<User> getUsers(){
		Session session=HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
		List<User> users=session.createCriteria(User.class).list();
		session.getTransaction().commit();
	    session.close();
		return users;
	}
	
	
	public User findByUserId(int userId){
		Session session=HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Query query=session.createQuery("from User where userId=:userId");
		query.setInteger("userId", userId);
		User resultUser=(User)query.uniqueResult();
	    session.getTransaction().commit();
	    session.close();
	    return resultUser;
	}
}
