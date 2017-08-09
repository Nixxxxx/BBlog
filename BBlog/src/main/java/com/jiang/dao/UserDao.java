package com.jiang.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jiang.entity.PageBean;
import com.jiang.entity.User;

@Repository
public class UserDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public boolean insert(User user){
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(user);
		tr.commit();
		session.close();
		return true;
	}
	
	public boolean delete(int id){
		User user = this.findById(id);
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.delete(user); 
		tr.commit();
		session.close();
		return true;
	}
	
	public boolean update(User user){
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.update(user);
		tr.commit();
		session.close();
		return true;
	}
	
	public boolean changePassword(int id,String password){
		User user = this.findById(id);
		user.setPassword(password);
		this.hibernateTemplate.merge(user);
		return true;
	}
	
	public List<User> findList(PageBean pageBean){
		StringBuffer sb = new StringBuffer("from User");
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q  =  session.createQuery(sb.toString());
		q.setFirstResult(pageBean.getStart());
        q.setMaxResults(pageBean.getPageSize());
        @SuppressWarnings("unchecked")
		List<User> userList = q.list();
        tx.commit();
        session.close();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return (List<User>) this.hibernateTemplate.find("from User");
	}
	
	public User findById(int id){
		return this.hibernateTemplate.get(User.class, id);
	}
}
