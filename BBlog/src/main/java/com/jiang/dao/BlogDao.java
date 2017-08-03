package com.jiang.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;

@Repository
public class BlogDao {

	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@Resource
	private SessionFactory sessionFactory;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void insert(Blog blog) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(blog);
		tx.commit();
		session.close();
	}
	
	public Blog update(Blog blog){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.merge(blog);
		tx.commit();
		session.close();
		Blog resultBlog = findById(blog.getId());
		return resultBlog;
	}

	public void delete(int id) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.delete(id);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Blog> findByTypeId(PageBean pageBean, int typeId) {
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
	public List<Blog> findAll(){
		return (List<Blog>) this.hibernateTemplate.find("from Blog");
	}
	
	public Blog findById(int id){
		return this.hibernateTemplate.get(Blog.class, id);
	}

}
