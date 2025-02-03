DROP DATABASE IF EXISTS reservation_db;
DROP USER IF EXISTS `fligthadmin`@`localhost`;
CREATE DATABASE IF NOT EXISTS reservation_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `fligthadmin`@`localhost` IDENTIFIED WITH mysql_native_password BY 'fligthadmin';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `reservation_db`.* TO `fligthadmin`@`localhost`;