SET  FOREIGN_KEY_CHECKS=0;
load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/user.txt' into table `user`;
load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/authors.txt' into table `author`;
load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/book.txt' into table `book`;
load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/category.txt' into table `category`;
/*load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/order.txt' into table `order`;
load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/customer_order.txt' into table `cunstomer_order`;
load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/order_item.txt' into table `order_item`;*/
load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/publisher.txt' into table `publisher`;
load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/p_phone.txt' into table `publisherphone`;
