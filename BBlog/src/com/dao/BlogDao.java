package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Blog;
import com.entity.PageBean;
import com.util.HibernateUtil;

public class BlogDao {

	public Blog findById(int blogId){
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
	    Query query=session.createQuery("from Blog where blogId=:blogId");
		query.setInteger("blogId",blogId);
		Blog resultBlog=(Blog)query.uniqueResult();
		tx.commit();
		session.close();
		return resultBlog;
	}
	
	
	public List<Blog> find(PageBean pageBean, int typeId){
		StringBuffer sb=new StringBuffer("from Blog");
		if(typeId!=0){
			sb.append(" and typeId="+typeId);
		}
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		Query q = session.createQuery(sb.toString());
		q.setFirstResult(pageBean.getStart());
        q.setMaxResults(pageBean.getPageSize());
        List<Blog> blogList=q.list();
        tx.commit();
        session.close();
		return blogList;
	}
	
	public Blog saveOrUpdate(Blog blog){
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		session.merge(blog);
		tx.commit();
		session.close();
		Blog resultBlog=findById(blog.getBlogId());
		return resultBlog;
	}
	
	public void delete(Blog blog){
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(blog);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Blog> findByTypeId(int typeId){
		StringBuffer sb=new StringBuffer("from Blog");
		if(typeId!=0){
			sb.append(" and typeId="+typeId);
		}
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		Query q = session.createQuery(sb.toString());
        List<Blog> blogList=q.list();
        tx.commit();
        session.close();
		return blogList;
	}
	
}
