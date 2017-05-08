package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory=buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        Configuration configuration=new Configuration().configure(); // ʵ���������ļ�
        ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); // ʵ��������Ǽ�
        return configuration.buildSessionFactory(serviceRegistry); // ��ȡSession����
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
