# RestAPIWithJersey2
Spring boot sample app to create rest API with JERSEY2.0, JPA, MYSQL .

Create table :

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL UNIQUE,
  `password` varchar(256) DEFAULT NULL,
  `mobile_no` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1

Update application.properties values according yours database setup.

spring.datasource.url=jdbc:mysql://localhost:3306/users
spring.datasource.username=*******
spring.datasource.password=*******


<B>Rest API for : </b>
1. Add user
2. Update user
3. Delete user
4. Get user by id.
5. Get all user list
6. User login
7. File upload 

<b>Basic authorization security implemented.</b></br>

<b>Cross-origin resource sharing implemented<b></br>

<b>Password is stored in database in encripted form.</b>
