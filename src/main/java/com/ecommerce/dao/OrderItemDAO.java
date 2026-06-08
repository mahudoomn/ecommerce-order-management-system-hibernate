package com.ecommerce.dao;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderItemDAO {
    private final SessionFactory sf = HibernateUtil.getSessionFactory();

    public void saveOrderItem(OrderItem orderItem){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(orderItem);
        transaction.commit();
        session.close();
    }

    public OrderItem getOrderItemById(int id){
        Session session = sf.openSession();
        OrderItem orderItem = session.find(OrderItem.class, id);
        session.close();
        return orderItem;
    }

    public List<OrderItem> getAllOrderItems(){
        Session session = sf.openSession();
        Query<OrderItem> query = session.createQuery("from OrderItem", OrderItem.class);
        List<OrderItem> orderItemList = query.getResultList();
        session.close();
        return orderItemList;
    }

    public boolean deleteOrderItem(int id){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        OrderItem orderItem = session.find(OrderItem.class, id);
        if(orderItem == null){
            transaction.rollback();
            session.close();
            return false;
        }
        session.remove(orderItem);
        transaction.commit();
        session.close();
        return true;
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId){
        Session session = sf.openSession();
        Query<OrderItem> query = session.createQuery(
                "from OrderItem oi where oi.order.orderId = :orderId",
                OrderItem.class
        );
        query.setParameter("orderId", orderId);
        List<OrderItem> items = query.getResultList();
        session.close();
        return items;
    }

    public void deleteOrderItem(OrderItem item){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        session.remove(session.merge(item));
        transaction.commit();
        session.close();
    }
}
