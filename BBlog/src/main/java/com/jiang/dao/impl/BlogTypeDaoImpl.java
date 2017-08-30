package com.jiang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jiang.dao.BlogTypeDao;
import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;

@Repository
public class BlogTypeDaoImpl implements BlogTypeDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	

	public void insert(BlogType blogType) {
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(blogType);
		tr.commit();
		session.close();
	}

	public void update(BlogType blogType) {
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.merge(blogType);
		tr.commit();
		session.close();
	}

	public void delete(Integer id) {
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.delete(this.findById(id));
		tr.commit();
		session.close();
	}

	
	public List<BlogType> findList(PageBean pageBean) {
		StringBuffer sb = new StringBuffer("from BlogType");
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q  =  session.createQuery(sb.toString());
		q.setFirstResult(pageBean.getStart());
        q.setMaxResults(pageBean.getPageSize());
        @SuppressWarnings("unchecked")
		List<BlogType> blogTypeList = q.list();
        tx.commit();
        session.close();
		return blogTypeList;
	}


	public List<BlogType> countList() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Object[]> counts = session.createSQLQuery("select typeId,count(typeId) from t_blog group by typeId").list();
		List<BlogType> blogTypeList = this.findAll();
		int i = 0;
		for(BlogType blogType:blogTypeList){
			if(i < counts.size()){
				int typeId = Integer.parseInt(counts.get(i)[0].toString());
				if(blogType.getId() == typeId){
					blogType.setCount(Integer.parseInt(counts.get(i++)[1].toString()));
				}
			}
		}
		tx.commit();
		session.close();
		return blogTypeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlogType> findAll(){
		return (List<BlogType>) this.hibernateTemplate.find("from BlogType");
	}
	
	public BlogType findById(Integer id){
		return this.hibernateTemplate.get(BlogType.class, id);
	}

}
