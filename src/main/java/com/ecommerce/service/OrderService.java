package com.ecommerce.service;

import com.ecommerce.dao.CustomerDAO;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Orders;

import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();

    public String addOrder(Orders order){
        try{
            orderDAO.saveOrder(order);
            return "Order added successfully.";
        }catch (Exception e){
            return "Failed to add the order.";
        }
    }

    public Orders getOrderById(int id){
        Orders order = orderDAO.getOrderById(id);
        if(order == null){
            System.out.println("Order not found");
        }
        return order;
    }

    public List<Orders> getAllOrders(){
        List<Orders> orders = orderDAO.getAllOrders();
        if(orders == null || orders.isEmpty()){
            System.out.println("No orders found");
        }
        return orders;
    }

    public String deleteOrder(int id){

        boolean deleted = orderDAO.deleteOrder(id);
        if(deleted){
            return "Order deleted successfully";
        }
        return "Order not found.";
    }
}
