package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.entity.User;
import com.entity.PageBean;

@Repository
public class UserDao {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@Resource
	private SessionFactory sessionFactory;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate  =  hibernateTemplate;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory  =  sessionFactory;
	}

	
	public boolean save(User user){
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
		return true;
	}
	
	public boolean delete(int id){
		User user = this.findById(id);
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tr = session.beginTransaction();
		session.delete(user); 
		tr.commit();
		session.close();
		return true;
	}
	
	public boolean update(User user){
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
		return true;
	}
	
	public boolean changePassword(int id,String password){
		User user = this.findById(id);
		user.setPassword(password);
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.merge(user);
		tx.commit();
		session.close();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> find(PageBean pageBean, User s_user){
		StringBuffer sb = new StringBuffer("from User");
//		if(s_User! = null){
//			if(StringUtil.isNotEmpty(s_User.getNumber())){
//				sb.append(" and deptName like '%"+s_User.getName()+"%'");
//			}
//		}
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q  =  session.createQuery(sb.toString());
		q.setFirstResult(pageBean.getStart());
        q.setMaxResults(pageBean.getPageSize());
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
