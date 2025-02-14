DROP DATABASE IF EXISTS clinicals_db;
DROP USER IF EXISTS `clinicadmin`@`localhost`;
CREATE DATABASE IF NOT EXISTS clinicals_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `clinicadmin`@`localhost` IDENTIFIED WITH mysql_native_password BY 'clinicadmin';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `clinicals_db`.* TO `clinicadmin`@`localhost`;