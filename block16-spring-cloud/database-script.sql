CREATE DATABASE IF NOT EXISTS db_block16_back;
CREATE DATABASE IF NOT EXISTS db_block16_front;

USE db_block16_back;
GRANT ALL PRIVILEGES ON *.* TO 'user'@'%';

USE db_block16_front;
GRANT ALL PRIVILEGES ON *.* TO 'user'@'%';