package com.ecommerce.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try{
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(com.ecommerce.entity.Customer.class)
                    .addAnnotatedClass(com.ecommerce.entity.OrderItem.class)
                    .addAnnotatedClass(com.ecommerce.entity.Orders.class)
                    .addAnnotatedClass(com.ecommerce.entity.Product.class)

                    .buildSessionFactory();
        }catch (Exception e){
            System.out.println("SessionFactory creation failed!");
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
