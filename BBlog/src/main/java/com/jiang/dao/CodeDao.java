package com.jiang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jiang.entity.Code;

@Repository
public class CodeDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public boolean insert(Code code){
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(code);
		tr.commit();
		session.close();
		return true;
	}
	
	public boolean delete(int id){
		Code code = this.findById(id);
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.delete(code); 
		tr.commit();
		session.close();
		return true;
	}
	
	public Code findById(int id){
		return this.hibernateTemplate.get(Code.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Code> findAll(){
		return (List<Code>) this.hibernateTemplate.find("from Code");
	}
}
