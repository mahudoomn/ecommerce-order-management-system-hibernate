# E-Commerce Order Management System

## Overview

A console-based E-Commerce Order Management System developed using Java, Hibernate ORM, and PostgreSQL. The application allows management of customers, products, orders, and inventory through a menu-driven interface.

## Technologies Used

* Java
* Hibernate ORM
* PostgreSQL
* Maven
* HQL (Hibernate Query Language)

## Features

### Customer Management

* Add Customer
* View Customer By ID
* View All Customers
* Update Customer Name
* Delete Customer

### Product Management

* Add Product
* View Product By ID
* View All Products
* Update Product Name
* Update Product Price
* Update Product Stock
* Delete Product
* Search Product By Name
* Low Stock Report

### Order Management

* Place Order
* View Customer Orders
* View Order Details
* Cancel Order

### Inventory Management

* Automatic Stock Reduction During Order Placement
* Automatic Stock Restoration During Order Cancellation

## Entity Relationships

### Customer → Orders

One Customer can have multiple Orders.

### Orders → OrderItem

One Order can contain multiple Order Items.

### Product → OrderItem

One Product can appear in multiple Order Items.

## Project Structure

src/main/java

* entity
* dao
* service
* util
* Main.java

## How To Run

1. Create PostgreSQL database:

```sql
CREATE DATABASE ecommerce_db;
```

2. Update database credentials in `hibernate.cfg.xml`.

3. Build the project:

```bash
mvn clean install
```

4. Run Main.java

5. Use the menu-driven console application.

## Hibernate Concepts Used

* Entity Mapping
* Primary Key Generation
* One-To-Many Mapping
* Many-To-One Mapping
* Join Columns
* SessionFactory
* Session Management
* Transactions
* HQL Queries

## Future Enhancements

* Spring Core Integration
* Spring Boot REST APIs
* Spring Data JPA
* JWT Authentication
* Frontend Integration

## Author

Mahudoom Naina
