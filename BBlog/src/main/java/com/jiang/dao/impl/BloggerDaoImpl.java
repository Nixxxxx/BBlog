package com.jiang.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jiang.dao.BloggerDao;
import com.jiang.entity.Blogger;

@Repository
public class BloggerDaoImpl implements BloggerDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean insert(Blogger blogger){
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(blogger);
		tr.commit();
		session.close();
		return true;
	}
	
	public boolean delete(int id){
		Blogger blogger = this.findById(id);
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.delete(blogger); 
		tr.commit();
		session.close();
		return true;
	}
	
	public boolean update(Blogger blogger){
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.update(blogger);
		tr.commit();
		session.close();
		return true;
	}
	

	public Blogger findById(int id){
		return this.hibernateTemplate.get(Blogger.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Blogger> findAll() {
		return (List<Blogger>) this.hibernateTemplate.find("from Blogger");
	}
}
