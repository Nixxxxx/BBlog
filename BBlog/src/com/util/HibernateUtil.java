package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory=buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        Configuration configuration=new Configuration().configure(); // 实例化配置文件
        ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); // 实例化服务登记
        return configuration.buildSessionFactory(serviceRegistry); // 获取Session工厂
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
