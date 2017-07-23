package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.entity.Blog;
import com.entity.BlogType;
import com.entity.PageBean;

@Repository
public class BlogTypeDao {

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
	

	public void save(BlogType blogType) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(blogType);
		tx.commit();
		session.close();
	}

	public void update(BlogType blogType) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.merge(blogType);
		tx.commit();
		session.close();
	}

	public void delete(int id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.delete(this.findById(id));
		tx.commit();
		session.close();
	}

	public List<BlogType> blogTypeList(PageBean pageBean) {
		StringBuffer sb = new StringBuffer("from BlogType");
//		if(s_User! = null){
//			if(StringUtil.isNotEmpty(s_User.getNumber())){
//				sb.append(" and deptName like '%"+s_User.getName()+"%'");
//			}
//		}
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q  =  session.createQuery(sb.toString());
		q.setFirstResult(pageBean.getStart());
        q.setMaxResults(pageBean.getPageSize());
        @SuppressWarnings("unchecked")
		List<BlogType> blogTypeList = q.list();
        blogTypeList = this.setCount(blogTypeList);
        tx.commit();
        session.close();
		return blogTypeList;
	}

	public List<BlogType> setCount(List<BlogType> blogTypeList) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Blog.class)
				.setProjection(Projections.projectionList().add(Projections.property("typeId"))
						.add(Projections.count("typeId")).add(Projections.groupProperty("typeId")))
				.addOrder(Order.asc("typeId"));
		@SuppressWarnings("unchecked")
		List<Object[]> counts = criteria.list();
		for(Object count :counts){
			System.out.println(count.toString());
		}
//		for(BlogType blogType:blogTypeList){
//			for(Object count:counts){
//				if(blogType.getId() == count[0]){
//					blogType.setCount(count[1]);
//				}
//			}
//		}
		tx.commit();
		session.close();
		return blogTypeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlogType> findAll(){
		return (List<BlogType>) this.hibernateTemplate.find("from BlogType");
	}
	
	public BlogType findById(int id){
		return this.hibernateTemplate.get(BlogType.class, id);
	}
}
