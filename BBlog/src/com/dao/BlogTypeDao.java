package com.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.entity.Blog;
import com.entity.BlogType;
import com.util.HibernateUtil;

public class BlogTypeDao {

	public BlogType search(int blogTypeId){
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
	    Query query=session.createQuery("from BlogType where blogTypeId=:blogTypeId");
		query.setInteger("blogTypeId", blogTypeId);
		BlogType resultBlogType=(BlogType)query.uniqueResult();
		return resultBlogType;
	}
	
	public void save(BlogType blogType){
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		session.save(blogType);
		tx.commit();
		session.close();
	}
	
	public void update(BlogType blogType){
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		session.merge(blogType);
		tx.commit();
		session.close();
	}
	
	public void delete(BlogType blogType){
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;   
		tx=session.beginTransaction();  
		session.delete(blogType);  
		tx.commit();  
		session.close(); 
	}
	
	public List<BlogType> blogTypeList(){
		Session session=HibernateUtil.getSessionFactory().openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
		List<BlogType> blogTypeList=session.createCriteria(BlogType.class).list();
		session.getTransaction().commit();
		session.close();
		return blogTypeList;
	}
	
	public List<BlogType> blogTypeCountList(){
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
	    Criteria criteria=session.createCriteria(Blog.class)
	    	.setProjection(Projections.projectionList()
            .add(Projections.property("typeName"))
            .add(Projections.count("typeId"))
            .add(Projections.groupProperty("typeId"))
	    	).addOrder(Order.asc("typeId"));
	    List<Object[]> lists=criteria.list();
	    List<BlogType> blogTypeCountList =new ArrayList<BlogType>();
	    Object[] obj=null;
    	for(Iterator<Object[]> it=lists.iterator();it.hasNext();){
    		obj=it.next().clone();
    		BlogType blogType=new BlogType((int) obj[2],(String)obj[0], new Long((long) obj[1]).intValue() );
    		blogTypeCountList.add(blogType);
    	}
		tx.commit();
		session.close();
		return blogTypeCountList;
	}
	
}
