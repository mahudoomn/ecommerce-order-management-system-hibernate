package com.ecommerce.service;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.OrderItemDAO;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Orders;

import java.util.List;

public class OrderItemService {
    private final OrderItemDAO orderItemDAO = new OrderItemDAO();

    public String addOrder(OrderItem orderItem){
        try{
            orderItemDAO.saveOrderItem(orderItem);
            return "Order Items added successfully.";
        }catch (Exception e){
            return "Failed to add the order items.";
        }
    }

    public OrderItem getOrderItemById(int id){
        OrderItem orderItem = orderItemDAO.getOrderItemById(id);
        if(orderItem == null){
            System.out.println("Order item not found");
        }
        return orderItem;
    }

    public List<OrderItem> getAllOrderItems(){
        List<OrderItem> orderItemList = orderItemDAO.getAllOrderItems();
        if(orderItemList == null || orderItemList.isEmpty()){
            System.out.println("No order item list found");
        }
        return orderItemList;
    }

    public String deleteOrderItem(int id){

        boolean deleted = orderItemDAO.deleteOrderItem(id);
        if(deleted){
            return "Order Item deleted successfully";
        }
        return "Order Item not found.";
    }
}
