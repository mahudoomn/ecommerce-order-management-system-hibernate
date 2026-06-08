package com.ecommerce.dao;

import com.ecommerce.entity.Customer;
import com.ecommerce.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDAO {
    private final SessionFactory sf = HibernateUtil.getSessionFactory();

    public void saveCustomer(Customer customer){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(customer);
        transaction.commit();
        session.close();
    }

    public Customer getCustomerById(int id){
        Session session = sf.openSession();
        Customer customer = session.find(Customer.class, id);
        session.close();
        return customer;
    }

    public List<Customer> getAllCustomer(){
        Session session = sf.openSession();
        Query<Customer> query = session.createQuery("from Customer", Customer.class);
        List<Customer> customerList = query.getResultList();
        session.close();
        return customerList;
    }

    public boolean updateCustomerName(int id, String newName){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.find(Customer.class, id);
        if(customer == null){
            transaction.rollback();
            session.close();
            return false;
        }
        customer.setName(newName);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean deleteCustomer(int id){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.find(Customer.class, id);
        if(customer == null){
            transaction.rollback();
            session.close();
            return false;
        }
        session.remove(customer);
        transaction.commit();
        session.close();
        return true;
    }
}
