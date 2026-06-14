package com.ecommerce;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Product;
import com.ecommerce.service.*;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();
        OrderManagementService orderManagementService = new OrderManagementService();

        while(true){

            System.out.println("\n========= E-Commerce Management =========");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer By ID");
            System.out.println("3. View All Customers");
            System.out.println("4. Update Customer Name");
            System.out.println("5. Delete Customer");
            System.out.println("\n6. Add Product");
            System.out.println("7. View Product By ID");
            System.out.println("8. View All Products");
            System.out.println("9. Update Product Name");
            System.out.println("10. Update Product Price");
            System.out.println("11. Update Product Stock");
            System.out.println("12. Delete Product");
            System.out.println("\n13. Search Product");
            System.out.println("14. Low Stock Report");
            System.out.println("\n15. Place Order");
            System.out.println("16. View Customer Orders");
            System.out.println("17. View Order Details");
            System.out.println("18. Cancel Order");
            System.out.println("19. Search Products By Price Range");
            System.out.println("0. Exit");

            int choice = sc.nextInt();

            switch(choice){

                case 1:
                    // Add Customer
                    sc.nextLine();
                    Customer customer = new Customer();
                    System.out.println("Enter Name: ");
                    customer.setName(sc.nextLine());

                    System.out.println("Enter Email: ");
                    customer.setEmail(sc.nextLine());

                    System.out.println("Enter Phone: ");
                    customer.setPhone(sc.nextLine());

                    System.out.println(customerService.addCustomer(customer));
                break;

                case 2:
                    // Get Customer By ID
                    System.out.println("Enter Customer ID: ");
                    int customerID = sc.nextInt();
                    Customer c = customerService.getCustomerById(customerID);
                    if(c != null){
                        System.out.println(c);
                    }
                break;

                case 3:
                    // View All Customers
                    List<Customer> customers = customerService.getAllCustomer();
                    for(Customer customer1: customers){
                        System.out.println(customer1);
                    }
                break;

                case 4:
                    // Update Customer Name
                    System.out.println("Enter Customer ID: ");
                    int customerID2 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.println(customerService.updateCustomerName(customerID2, newName));
                break;

                case 5:
                    // Delete Customer
                    System.out.println("Enter Customer ID: ");
                    int customerID3 = sc.nextInt();
//                    sc.nextLine();
                    System.out.println(customerService.deleteCustomer(customerID3));
                break;

                case 6:
                    // Add Product
                    sc.nextLine();
                    Product product = new Product();
                    System.out.println("Enter Product Name: ");
                    product.setProductName(sc.nextLine());

                    System.out.println("Enter Product Price: ");
                    product.setPrice(sc.nextDouble());

                    System.out.println("Enter Product Stock: ");
                    product.setStock(sc.nextInt());

                    System.out.println(
                            productService.addProduct(product)
                    );
                break;

                case 7:
                    // View Product By ID
                    System.out.println("Enter Product ID: ");
                    int productID = sc.nextInt();
                    Product productId1 = productService.getProductById(productID);
                    if(productId1 != null){
                        System.out.println(productId1);
                    }
                break;

                case 8:
                    // View All Product
                    List<Product> products = productService.getAllProducts();
                    for(Product productId2 : products){
                        System.out.println(productId2);
                    }
                break;

                case 9:
                    // Update Product Name
                    System.out.println("Enter Product ID: ");
                    int productId3 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter New Product Name:");
                    String newProductName = sc.nextLine();

                    System.out.println(
                            productService.updateProductName(productId3, newProductName)
                    );
                break;

                case 10:
                    // Update Product Price
                    System.out.println("Enter Product ID: ");
                    int productId4 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter New Product Price:");
                    double newPriceName = sc.nextDouble();

                    System.out.println(
                            productService.updateProductPrice(productId4, newPriceName)
                    );
                break;

                case 11:
                    // Update Product Stock
                    System.out.println("Enter Product ID: ");
                    int productId5 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter New Product Stock:");
                    int newStockName = sc.nextInt();

                    System.out.println(
                            productService.updateProductStock(productId5, newStockName)
                    );
                break;

                case 12:
                    // Delete Product
                    System.out.println("Enter Product ID: ");
                    int productId6 = sc.nextInt();
                    System.out.println(
                            productService.deleteProduct(productId6)
                    );
                break;

                case 13:
                    // Search Product
                    sc.nextLine();
                    System.out.println("Enter Product Name: ");
                    String productName = sc.nextLine();

                    List<Product> productList = productService.searchProductByName(productName);
                    for(Product product1 : productList){
                        System.out.println(product1);
                    }
                break;

                case 14:
                    // Low Stock Report
                    System.out.println("Enter Stock Threshold: ");
                    int threshold = sc.nextInt();
                    List<Product> productList1 = productService.getLowStockProducts(threshold);
                    for(Product product1: productList1){
                        System.out.println(product1);
                    }
                 break;

                case 15:
                    // Place Order
                    System.out.println("Enter Customer ID: ");
                    int customerId = sc.nextInt();
                    System.out.println("Enter Product ID: ");
                    int productId = sc.nextInt();
                    System.out.println("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    System.out.println(
                            orderManagementService.placeOrder(customerId, productId, quantity)
                    );
                break;

                case 16:
                    // View Customer Orders
                    System.out.println("Enter Customer ID: ");
                    orderManagementService.viewCustomerOrders(sc.nextInt());
                    break;

                case 17:
                    // View Order Details
                    System.out.println("Enter Order ID: ");
                    orderManagementService.viewOrderDetails(sc.nextInt());
                    break;
                case 18:
                    // Cancel Order
                    System.out.println("Enter Order ID: ");
                    orderManagementService.cancelOrder(sc.nextInt());
                    break;
                case 19:
                    // Search Product By Price Range
                    System.out.println("Enter the Minimum Price: ");
                    double minimumPrice = sc.nextDouble();
                    System.out.println("Enter the Maximum Price: ");
                    double maximumPrice = sc.nextDouble();
                    List<Product> productListByPriceRange = productService.getProductByPriceRange(minimumPrice, maximumPrice);
                    for(Product product1: productListByPriceRange){
                        System.out.println(product1);
                    }
                    break;
                case 0:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
