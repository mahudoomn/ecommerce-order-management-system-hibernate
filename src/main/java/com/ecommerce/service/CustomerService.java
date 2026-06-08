package com.ecommerce.service;

import com.ecommerce.dao.CustomerDAO;
import com.ecommerce.entity.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDAO customerDAO = new CustomerDAO();

    public String addCustomer(Customer customer){
        try{
            customerDAO.saveCustomer(customer);
            return "Customer added successfully";
        }catch (Exception e){
            return "Failed to add customer";
        }
    }

    public Customer getCustomerById(int id){
        Customer customer = customerDAO.getCustomerById(id);
        if(customer == null){
            System.out.println("Customer not found");
        }
        return customer;
    }

    public List<Customer> getAllCustomer(){
        List<Customer> customer = customerDAO.getAllCustomer();
        if(customer == null || customer.isEmpty()){
            System.out.println("No customers found");
        }
        return customer;
    }

    public String updateCustomerName(int id, String newName){

        boolean updated = customerDAO.updateCustomerName(id, newName);
        if(updated){
            return "Customer record updated successfully";
        }
        return "Customer not found.";
    }

    public String deleteCustomer(int id){

        boolean deleted = customerDAO.deleteCustomer(id);
        if(deleted){
            return "Customer record deleted successfully";
        }
        return "Customer not found.";
    }


}
