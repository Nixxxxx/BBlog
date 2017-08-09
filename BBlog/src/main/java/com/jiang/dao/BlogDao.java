package com.jiang.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;

@Repository
public class BlogDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Blog blog) {
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(blog);
		tr.commit();
		session.close();
	}
	
	public Blog update(Blog blog){
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.merge(blog);
		tr.commit();
		session.close();
		return findById(blog.getId());
	}

	public void delete(int id) {
		Blog blog = this.findById(id);
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.delete(blog);
		tr.commit();
		session.close();
	}
	
	public List<Blog> findListByTypeId(PageBean pageBean, int typeId) {
		StringBuffer sb = new StringBuffer("from Blog");
		if (typeId != 0) {
			sb.append(" where typeId = " + typeId);
		}
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(sb.toString());
		q.setFirstResult(pageBean.getStart());
		q.setMaxResults(pageBean.getPageSize());
		@SuppressWarnings("unchecked")
		List<Blog> blogList = q.list();
		tx.commit();
		session.close();
		return blogList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Blog> findByTypeId(int typeId) {
		return (List<Blog>) this.hibernateTemplate.find("from Blog where blogType.id= " + typeId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Blog> findAll(){
		return (List<Blog>) this.hibernateTemplate.find("from Blog");
	}
	
	public Blog findById(int id){
		return this.hibernateTemplate.get(Blog.class, id);
	}

}
