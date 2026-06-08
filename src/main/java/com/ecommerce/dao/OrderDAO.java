package com.ecommerce.dao;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Orders;
import com.ecommerce.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAO {
    private final SessionFactory sf = HibernateUtil.getSessionFactory();

    public void saveOrder(Orders order){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(order);
        transaction.commit();
        session.close();
    }

    public Orders getOrderById(int id){
        Session session = sf.openSession();
        Orders order = session.find(Orders.class, id);
        session.close();
        return order;
    }

    public List<Orders> getAllOrders(){
        Session session = sf.openSession();
        Query<Orders> query = session.createQuery("from Orders", Orders.class);
        List<Orders> orderList = query.getResultList();
        session.close();
        return orderList;
    }

    public boolean deleteOrder(int id){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Orders order = session.find(Orders.class, id);
        if(order == null){
            transaction.rollback();
            session.close();
            return false;
        }
        session.remove(order);
        transaction.commit();
        session.close();
        return true;
    }

    public List<Orders> getOrderByCustomerId(int customerId){
        Session session = sf.openSession();

        Query<Orders> query = session.createQuery(
                "from Orders o where o.customer.customerId = :customerId",
                Orders.class
        );
        query.setParameter("customerId", customerId);
        List<Orders> ordersList = query.getResultList();
        session.close();
        return ordersList;
    }
}
