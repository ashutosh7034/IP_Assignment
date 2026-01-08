-- Employee Management System - MySQL Schema (DDL only)
-- Safe to run multiple times

-- 1) Create database
CREATE DATABASE IF NOT EXISTS ems_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE ems_db;

-- 2) Drop tables if you need a clean slate (optional)
-- DROP TABLE IF EXISTS user_roles;
-- DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS employees;

-- 3) employees table (matches com.employee.entity.EmployeeEntity)
CREATE TABLE IF NOT EXISTS employees (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  name         VARCHAR(255) NOT NULL,
  department   VARCHAR(100) NOT NULL,
  salary       DOUBLE NOT NULL,
  email        VARCHAR(255) UNIQUE,
  phone        VARCHAR(50),
  position     VARCHAR(100),
  status       VARCHAR(20),
  created_at   DATETIME,
  updated_at   DATETIME,
  KEY idx_employees_department (department)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4) users table (matches com.employee.entity.User)
CREATE TABLE IF NOT EXISTS users (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  username     VARCHAR(100) NOT NULL UNIQUE,
  email        VARCHAR(255) NOT NULL UNIQUE,
  password     VARCHAR(100) NOT NULL,
  full_name    VARCHAR(255),
  is_active    TINYINT(1) NOT NULL DEFAULT 1,
  created_at   DATETIME,
  updated_at   DATETIME,
  employee_id  BIGINT NULL,
  CONSTRAINT fk_users_employee
    FOREIGN KEY (employee_id) REFERENCES employees(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5) user_roles (ElementCollection for roles)
CREATE TABLE IF NOT EXISTS user_roles (
  user_id  BIGINT NOT NULL,
  role     VARCHAR(50) NOT NULL,
  PRIMARY KEY (user_id, role),
  CONSTRAINT fk_roles_user
    FOREIGN KEY (user_id) REFERENCES users(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- NOTE ABOUT DATA SEEDING
-- This file intentionally contains only DDL (schema). Your Spring Boot app
-- seeds default data (admin user, sample employees, and linked users) on first run
-- when the tables are empty. This avoids bcrypt password mismatch issues.
-- If you still want SQL INSERTs, let me know the exact plaintext passwords
-- you want and I’ll generate proper BCrypt hashes for them.
