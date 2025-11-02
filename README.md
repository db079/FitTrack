# 🏋️‍♂️ Fitness Tracker Application

![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![React](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![License: MIT](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)


A full-stack **Fitness Tracker** web application built using:
- **Spring Boot (Java)** for the backend  
- **React.js** for the frontend  
- **MySQL** for the database  

This app allows users to register, set fitness goals, log exercises, and manage roles (User/Admin).

---

## 📁 Project Structure
```
fitness-tracker-application/
├── fitness-tracker/              # Backend (Spring Boot)
├── fitness-tracker-frontend/     # Frontend (React)
└── README.md
```

---

## ⚙️ Backend Setup (Spring Boot)

### 1️⃣ Create the MySQL Database
```sql
CREATE DATABASE fitnessapp;
```

### 2️⃣ Configure `application.properties`
In the backend folder (`fitness-tracker/src/main/resources/application.properties`), make sure you have:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fitnessapp
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### 3️⃣ Run the Backend Application
From your **STS IDE** or terminal:
```bash
cd fitness-tracker
mvn spring-boot:run
```
The backend runs on **http://localhost:8080**

---

## 💻 Frontend Setup (React)

### 1️⃣ Install Dependencies
```bash
cd fitness-tracker-frontend
npm install
```

### 2️⃣ Start the Frontend
```bash
npm start
```
The frontend runs on **http://localhost:3000**

---

## 🗄️ Database Tables

Once the backend runs, MySQL automatically creates the following tables:
```sql
SHOW TABLES;
+----------------------+
| Tables_in_fitnessapp |
+----------------------+
| custom_goal          |
| exercise             |
| goal                 |
| roles                |
| user_roles           |
| users                |
+----------------------+
```

---

## 🔐 Role Management

By default, the **roles** table is empty.  
Insert the initial roles:
```sql
INSERT INTO roles VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');
```

### ➕ Register a New User
Use the frontend registration page to create a user.  
Passwords are securely **hashed** before saving.

Check users:
```sql
SELECT * FROM users;
```

Example output:
```
+----+-----+-----------------+--------+-------+--------------------------------------------------------------+--------+
| id | age | email           | height | name  | password                                                     | weight |
+----+-----+-----------------+--------+-------+--------------------------------------------------------------+--------+
|  1 |  25 | admin@gmail.com |    170 | admin | $2a$10$o5aY4P1mpAefsEG5xl4BPOUvtj0ffmnN/6yvGKFuNd6NAUE9Lqg.6 |     70 |
+----+-----+-----------------+--------+-------+--------------------------------------------------------------+--------+
```

Check their role:
```sql
SELECT * FROM user_roles;
```
Example:
```
+---------+---------+
| user_id | role_id |
+---------+---------+
|       1 |       1 |
+---------+---------+
```

### 🧑‍💼 Promote the User to Admin
```sql
UPDATE user_roles SET role_id = 2 WHERE user_id = 1;
```
Now the user with ID `1` (`admin@gmail.com`) is an **Admin**.

---

## 🧠 Admin Features
- Add/manage exercises  
- Manage users  
- View and edit goals  
- Track overall progress  

---

## 🏃‍♂️ User Features
- Register and log in securely  
- Set personal fitness goals  
- Log exercises and view progress  
- Update personal details  

---

## 📂 exerciseData.json
This file contains pre-defined exercises.  
You can:
- Import it directly into MySQL, or  
- Add exercises from the **Admin dashboard** after login.

---

## 🧰 Tech Stack

| Category | Technology |
|-----------|-------------|
| **Frontend** | React.js, Axios, Bootstrap |
| **Backend** | Spring Boot, Spring Security, JPA, Hibernate |
| **Database** | MySQL |
| **Authentication** | JWT-based |
| **Build Tools** | Maven (backend), npm (frontend) |

---

## 🧑‍💻 Run the Whole Application

1️⃣ Start MySQL and create the database:
```sql
CREATE DATABASE fitnessapp;
```

2️⃣ Run the backend:
```bash
cd fitness-tracker
mvn spring-boot:run
```

3️⃣ Run the frontend:
```bash
cd fitness-tracker-frontend
npm start
```

4️⃣ Open your browser:
👉 [http://localhost:3000](http://localhost:3000)

---

## 📜 License
This project is open-source and free to use for learning and development.

---

### ✨ Developed by: *Your Name*
