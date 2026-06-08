package com.ecommerce.service;


import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Product;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAO();

    public String addProduct(Product product){
        try{
            productDAO.saveProduct(product);
            return "Product added successfully";
        }catch (Exception e){
            return "Failed to add product";
        }
    }

    public Product getProductById(int id){
        Product product = productDAO.getProductById(id);
        if(product == null){
            System.out.println("Product not found");
        }
        return product;
    }

    public List<Product> getAllProducts(){
        List<Product> products = productDAO.getAllProducts();
        if(products == null || products.isEmpty()){
            System.out.println("No products found");
        }
        return products;
    }

    public String updateProductName(int id, String newName){

        boolean updated = productDAO.updateProductName(id, newName);
        if(updated){
            return "Product new name updated successfully";
        }
        return "Product not found.";
    }

    public String updateProductPrice(int id, double newPrice){

        if(newPrice <= 0){
            return "Price must be greater than 0";
        }

        boolean updated = productDAO.updateProductPrice(id, newPrice);
        if(updated){
            return "Product new price updated successfully";
        }
        return "Product not found.";
    }

    public String updateProductStock(int id, int newStock){

        if(newStock <= 0){
            return "Stock must be greater than 0";
        }

        boolean updated = productDAO.updateProductStock(id, newStock);
        if(updated){
            return "Product new stock updated successfully";
        }
        return "Product not found.";
    }

    public String deleteProduct(int id){

        boolean deleted = productDAO.deleteProduct(id);
        if(deleted){
            return "Product deleted successfully";
        }
        return "Product not found.";
    }

    public List<Product> searchProductByName(String productName){
        List<Product> products = productDAO.searchProductByName(productName);
        if(products.isEmpty()){
            System.out.println("No products found");
        }
        return products;
    }

    public List<Product> getLowStockProducts(int threshold){
        List<Product> products = productDAO.getLowStockProduct(threshold);
        if (products.isEmpty()){
            System.out.println("No low stock products found");
        }
        return products;
    }
}
