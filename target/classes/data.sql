USE employee_management;

-- TRUNCATE TABLE employee;

INSERT INTO employee (id, employee_number, age, first_name, last_name, email, phone, gender, salary_id) VALUES  
(10001,10001,65,'Vince','McMahon','vince@gmail.com','12345001','Male', 1),
(10002,10002, 40,'Shane','McMahon','shane@gmail.com','12345002','Male', 2),
(10003,10003, 60, 'Linda','McMahon','linda@gmail.com','12345003','Female', 1),
(10004,10004, 35, 'Stephanie','McMahon','stephanie@gmail.com','12345004','Female', 1),
(10005,10005, 40, 'Chyna','Jean','chyna@gmail.com','12345005','Other', 3),
(10006,10006, 42, 'Bret','Hart','brethart@gmail.com','12345005','Male', 3)
;

--TRUNCATE TABLE address;

INSERT INTO address (id, address_line1, address_line2, zip_code, address_type, employee_id) VALUES 
(1,'Greenwich','Connecticut','123456','PERMANENT', 10001),
(2,'San Antonio','Texas','123456','RESIDENTIAL', 10001),
(3,'Toronto','Canada','123456','PERMANENT', 10002),
(4,'San Antonio','Texas','999456','RESIDENTIAL', 10002),
(5,'Death Valley','California','126456','PERMANENT', 10003),
(6,'Punjab','India','124456','RESIDENTIAL', 10003),
(7,'St Louis','Missouri','122456','PERMANENT', 10004),
(8,'El Paso','Texas','122456','RESIDENTIAL', 10004),
(9,'St Louis','Missouri','122456','PERMANENT', 10005),
(10,'San Diego','California','122466','RESIDENTIAL', 10005),
(11,'Calagary','Canada','122466','PERMANENT', 10006),
(12,'San Diego','California','122466','RESIDENTIAL', 10006)
;
