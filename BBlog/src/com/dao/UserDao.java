package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.entity.User;
import com.util.HibernateUtil;

public class UserDao {
	
	
	
	public User register(User user){
	    Session session=HibernateUtil.getSessionFactory().openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    session.save(user);
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
		User resultUser=login(user);
	    return resultUser;
	}
		
	
	public User login(User user){
	    Session session=HibernateUtil.getSessionFactory().openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    Query query=session.createQuery("from User where userName=:userName and password=:password");
		query.setString("userName", user.getUserName());
		query.setString("password", user.getPassword());
		User resultUser=(User)query.uniqueResult();
	    session.getTransaction().commit(); // 提交事务
	    session.close();
	    return resultUser;
	}
	
	public User checkUserName(String userName){
	    Session session=HibernateUtil.getSessionFactory().openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    Query query=session.createQuery("from User where userName=:userName");
		query.setString("userName", userName);
		User resultUser=(User)query.uniqueResult();
	    session.getTransaction().commit(); // 提交事务
	    session.close();
	    return resultUser;
	}
	
	public User update(User user){
	    Session session=HibernateUtil.getSessionFactory().openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    session.merge(user);
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
		User resultUser=login(user);
	    return resultUser;
	}

	public List<User> getUsers(){
		Session session=HibernateUtil.getSessionFactory().openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
		List<User> users=session.createCriteria(User.class).list();
		session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
		return users;
	}
	
}
