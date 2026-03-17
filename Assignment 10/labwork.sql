-- Drop and recreate database
DROP DATABASE IF EXISTS labwork;
CREATE DATABASE labwork;
USE labwork;

-- ======================
-- Table: salesman
-- ======================
CREATE TABLE salesman (
    salesman_id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    city VARCHAR(50),
    commission DECIMAL(4,2) CHECK (commission >= 0 AND commission <= 1)
);

-- ======================
-- Table: customer
-- ======================
CREATE TABLE customer (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(50) NOT NULL,
    city VARCHAR(50),
    grade INT CHECK (grade BETWEEN 100 AND 500),
    salesman_id INT,
    FOREIGN KEY (salesman_id) REFERENCES salesman(salesman_id)
);

-- ======================
-- Table: orders
-- ======================
CREATE TABLE orders (
    order_no INT PRIMARY KEY,
    purchase_amt DECIMAL(10,2) NOT NULL,
    order_date DATE,
    customer_id INT,
    salesman_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (salesman_id) REFERENCES salesman(salesman_id)
);

-- ======================
-- Insert sample data
-- ======================

-- Salesmen
INSERT INTO salesman VALUES
(5001, 'John Smith', 'New York', 0.15),
(5002, 'Alice Brown', 'Los Angeles', 0.12),
(5003, 'Robert White', 'Chicago', 0.10),
(5004, 'Emily Davis', 'Houston', 0.18);

-- Customers
INSERT INTO customer VALUES
(3001, 'Acme Corp', 'New York', 200, 5001),
(3002, 'Beta LLC', 'Los Angeles', 300, 5002),
(3003, 'Gamma Inc', 'Chicago', 150, 5003),
(3004, 'Delta Co', 'Houston', 400, 5004),
(3005, 'Epsilon Ltd', 'New York', 250, 5001);

-- Orders
INSERT INTO orders VALUES
(7001, 1500.50, '2026-03-01', 3001, 5001),
(7002, 250.00, '2026-03-02', 3002, 5002),
(7003, 3200.75, '2026-03-03', 3003, 5003),
(7004, 450.25, '2026-03-04', 3004, 5004),
(7005, 980.00, '2026-03-05', 3005, 5001),
(7006, 1200.00, '2026-03-06', 3001, 5001);