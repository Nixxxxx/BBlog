package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Blog;
import com.entity.BlogType;
import com.util.HibernateUtil;

public class BlogDao {

	public Blog search(int blogId){
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
	    Query query=session.createQuery("from Blog where blogId=:blogId");
		query.setInteger("blogId",blogId);
		Blog resultBlog=(Blog)query.uniqueResult();
		tx.commit();
		session.close();
		return resultBlog;
	}
	
	public List<Blog> blogListSearch(int typeId){
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
	    Query query=session.createQuery("from Blog where typeId=:typeId");
		query.setInteger("typeId",typeId);
		List<Blog> resultBlogList=(List<Blog>) query.list();
		tx.commit();
		session.close();
		return resultBlogList;
	}
	
	
	
	public Blog saveOrUpdate(Blog blog){
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		session.merge(blog);
		tx.commit();
		session.close();
		Blog resultBlog=search(blog.getBlogId());
		return resultBlog;
	}
	
	public void delete(Blog blog){
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(blog);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Blog> getBlogs(){
		Session session=HibernateUtil.getSessionFactory().openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
		List<Blog> blogs=session.createCriteria(Blog.class).list();
		session.getTransaction().commit();
		session.close();
		return blogs;
	}
	
}
