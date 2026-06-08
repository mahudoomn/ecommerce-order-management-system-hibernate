package com.ecommerce.service;

import com.ecommerce.dao.CustomerDAO;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.OrderItemDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Customer;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Product;

import java.time.LocalDate;
import java.util.List;

public class OrderManagementService {
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final OrderDAO orderDAO = new OrderDAO();
    private final OrderItemDAO orderItemDAO = new OrderItemDAO();

    public String placeOrder(int customerId, int productId, int quantity){
        Customer customer = customerDAO.getCustomerById(customerId);
        if(customer == null){
            return "Customer not found";
        }

        Product product = productDAO.getProductById(productId);
        if(product == null){
            return "Product not found";
        }

        if(quantity <= 0){
            return "Quantity must be greater than zero";
        }

        if(product.getStock() < quantity){
            return "Insufficient stock";
        }

        double subtotal = product.getPrice() * quantity;

        Orders order = new Orders();
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(subtotal);

        orderDAO.saveOrder(order);

        OrderItem item = new OrderItem();
        item.setQuantity(quantity);
        item.setSubtotal(subtotal);
        item.setOrders(order);
        item.setProduct(product);

        orderItemDAO.saveOrderItem(item);

        int remainingStock = product.getStock() - quantity;
        productDAO.updateProductStock(product.getProductId(), remainingStock);

        return "Order placed successfully";
    }

    public void viewCustomerOrders(int customerId){
        Customer customer = customerDAO.getCustomerById(customerId);

        if(customer == null){
            System.out.println("Customer not found");
            return;
        }
        System.out.println("Customer: " + customer.getName());

        List<Orders> ordersList = orderDAO.getOrderByCustomerId(customerId);
        if(ordersList == null){
            System.out.println("No orders found");
            return;
        }
        for(Orders order: ordersList){
            System.out.println("-------------------");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Total Amount: " + order.getTotalAmount());
        }
    }

    public void viewOrderDetails(int orderId){
        Orders order = orderDAO.getOrderById(orderId);
        if(order == null){
            System.out.println("Order not found");
            return;
        }
        List<OrderItem> items = orderItemDAO.getOrderItemsByOrderId(orderId);

        System.out.println("=========================");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Order Date: " + order.getOrderDate());


        System.out.println("\nItems:");

        for(OrderItem item: items){
            System.out.println(
                    item.getProduct().getProductName()
                    + " x "
                    + item.getQuantity()
                    + " = "
                    + item.getSubtotal()
            );
        }

        System.out.println("\nTotal Amount: " + order.getTotalAmount());
        System.out.println("===================================");
    }

    public String cancelOrder(int orderId){
        Orders order = orderDAO.getOrderById(orderId);
        if(order == null){
            return "Order not found";
        }

        List<OrderItem> items = orderItemDAO.getOrderItemsByOrderId(orderId);
        for(OrderItem item: items){
            Product product = item.getProduct();
            int restoredStock = product.getStock() + item.getQuantity();
            productDAO.updateProductStock(product.getProductId(), restoredStock);
            orderItemDAO.deleteOrderItem(item);
        }

        orderDAO.deleteOrder(orderId);
        return "Order cancel successfully";

    }
}
