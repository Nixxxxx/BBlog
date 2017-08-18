package com.jiang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jiang.dao.AdminDao;
import com.jiang.entity.Admin;
import com.jiang.entity.PageBean;

@Repository
public class AdminDaoImpl implements AdminDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	public boolean insert(Admin admin){
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(admin);
		tr.commit();
		session.close();
		return true;
	}
	
	public boolean delete(int id){
		Admin admin = this.findById(id);
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.delete(admin);
		tr.commit();
		session.close();
		return true;
	}
	
	public boolean update(Admin admin){
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.merge(admin);
		tr.commit();
		session.close();
		return true;
	}
	
	public boolean changePassword(int id,String password){
		Admin admin = this.findById(id);
		admin.setPassword(password);
		this.hibernateTemplate.merge(admin);
		return true;
	}
	
	public List<Admin> findList(PageBean pageBean){
		StringBuffer sb = new StringBuffer("from Admin");
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q  =  session.createQuery(sb.toString());
		q.setFirstResult(pageBean.getStart());
        q.setMaxResults(pageBean.getPageSize());
        @SuppressWarnings("unchecked")
		List<Admin> adminList = q.list();
        tx.commit();
        session.close();
		return adminList;
	}

	public Admin findById(int id){
		return this.hibernateTemplate.get(Admin.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Admin> findAll() {
		return (List<Admin>) this.hibernateTemplate.find("from Admin");
	}
}
