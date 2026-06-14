package com.ecommerce.dao;

import com.ecommerce.entity.Product;
import com.ecommerce.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {
    private final SessionFactory sf = HibernateUtil.getSessionFactory();

    public void saveProduct(Product product){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        session.close();
    }

    public Product getProductById(int id){
        Session session = sf.openSession();
        Product product = session.find(Product.class, id);
        session.close();
        return product;
    }

    public List<Product> getAllProducts(){
        Session session = sf.openSession();
        Query<Product> query = session.createQuery("from Product", Product.class);
        List<Product> productList = query.getResultList();
        session.close();
        return productList;
    }

    public boolean updateProductName(int id, String newName){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.find(Product.class, id);
        if(product == null){
            transaction.rollback();
            session.close();
            return false;
        }
        product.setProductName(newName);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean updateProductPrice(int id, double newPrice){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.find(Product.class, id);
        if(product == null){
            transaction.rollback();
            session.close();
            return false;
        }
        product.setPrice(newPrice);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean updateProductStock(int id, int newStock){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.find(Product.class, id);
        if(product == null){
            transaction.rollback();
            session.close();
            return false;
        }
        product.setStock(newStock);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean deleteProduct(int id){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.find(Product.class, id);
        if(product == null){
            transaction.rollback();
            session.close();
            return false;
        }
        session.remove(product);
        transaction.commit();
        session.close();
        return true;
    }

    public List<Product> searchProductByName(String productName){
        Session session = sf.openSession();
        Query<Product> query = session.createQuery(
                "from Product p where lower(p.productName) like lower(:productName)",
                Product.class
        );
        query.setParameter("productName", "%"+productName+"%");
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }

    public List<Product> getLowStockProduct(int threshold){
        Session session = sf.openSession();
        Query<Product> query = session.createQuery(
                "from Product p where p.stock < :threshold",
                Product.class
        );
        query.setParameter("threshold", threshold);
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }

    public List<Product> getProductByPriceRange(double minPrice, double maxPrice){
        Session session = sf.openSession();
        Query<Product> query = session.createQuery(
                "from Product p where p.price between :minPrice and :maxPrice",
                Product.class
        );
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        List<Product> productListsByPriceRange = query.getResultList();
        session.close();
        return productListsByPriceRange;
    }
}
