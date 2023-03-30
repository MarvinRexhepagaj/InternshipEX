

SELECT customers.customerNumber, customers.customerName, COUNT(orders.orderNumber) as orderCount
FROM customers
INNER JOIN orders ON customers.customerNumber = orders.customerNumber
GROUP BY customers.customerNumber, customers.customerName
HAVING COUNT(orders.orderNumber) = (
  SELECT MAX(orderCount)
  FROM (
    SELECT COUNT(orderNumber) as orderCount
    FROM orders
    GROUP BY customerNumber
  ) AS countTable

);


SELECT c.customerName, o.orderNumber, o.orderDate, od.productCode, od.quantityOrdered, od.priceEach
FROM customers c
JOIN orders o ON c.customerNumber = o.customerNumber
JOIN orderdetails od ON o.orderNumber = od.orderNumber
WHERE c.country = 'Germany'
ORDER BY c.customerName, o.orderNumber;

SELECT e.employeeNumber, e.firstName, e.lastName, SUM(p.amount) AS revenue
FROM employees e
INNER JOIN customers c ON e.employeeNumber = c.salesRepEmployeeNumber
INNER JOIN payments p ON c.customerNumber = p.customerNumber
GROUP BY e.employeeNumber
ORDER BY revenue DESC;

SELECT p.productName, od.quantityOrdered, od.priceEach
FROM orderdetails od
INNER JOIN orders o ON od.orderNumber = o.orderNumber
INNER JOIN products p ON od.productCode = p.productCode
WHERE o.orderDate BETWEEN DATE_SUB('2005-01-01', INTERVAL 1 MONTH) AND '2005-01-01';

CREATE TABLE `employeedetails` (
  `detailID` int NOT NULL AUTO_INCREMENT,
  `bankAccount` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  `personalEmail` varchar(50) NOT NULL,
  `employeeNumber` int NOT NULL,
  PRIMARY KEY (`detailID`),
  KEY `employeeNumber` (`employeeNumber`),
  CONSTRAINT `employeedetailsemployeedetails_ibfk_1` FOREIGN KEY (`employeeNumber`) REFERENCES `employees` (`employeeNumber`)
) 