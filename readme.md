# ☕ Milk Tea Ordering System — Backend

A **Spring Boot + MyBatis** backend service for a milk tea ordering system, focusing on **clean architecture, RESTful APIs, pagination, and business correctness**.

Built as a **portfolio project** to demonstrate real-world backend engineering practices.

---

## ✨ Features

* Create orders with multiple items
* Item-level customization (size / sugar / ice)
* Order lifecycle management:

    * `PENDING → COMPLETED / CANCELLED`
* Order list & order detail APIs
* Pagination & status filtering
* Unified API response & global exception handling
* Request validation & defensive programming

---

## 🛠 Tech Stack

* **Java 8+**
* **Spring Boot**
* **MyBatis (XML)**
* **MySQL**
* **Maven**
* **RESTful API**
* **Multi-module architecture**

---

## 📂 Project Structure

```
milk-tea-backend
├── milk-tea-controller   # REST controllers
├── milk-tea-service      # Business logic
├── milk-tea-dao          # MyBatis mappers & entities
├── milk-tea-common       # DTOs, enums, common responses
└── pom.xml
```

---

## 🗄 Database Model

* `mt_user`
* `mt_drink`
* `mt_order`
* `mt_order_item`

**Design highlights**

* Order items store customization details
* Order table only holds order-level data
* Clear one-to-many relationships

---

## 🔁 Order Status

| Code | Status    |
| ---- | --------- |
| 0    | PENDING   |
| 1    | COMPLETED |
| 2    | CANCELLED |

Status transitions are strictly validated in the service layer.

---

## 📡 API Examples

```
POST   /orders
GET    /orders/page?userId=1&page=1&pageSize=10&status=0
GET    /orders/{orderId}
PATCH  /orders/{orderId}/status
```

---

## 📦 Unified API Response

```json
{
  "code": 0,
  "message": "ok",
  "data": {}
}
```

Pagination response:

```json
{
  "total": 20,
  "page": 1,
  "pageSize": 10,
  "list": []
}
```

---

## ▶️ Run Locally

1. Create database:

```sql
CREATE DATABASE milk_tea;
```

2. Configure `application.yml` (or `application-local.yml`).

3. Start application:

```bash
mvn spring-boot:run
```

Server runs at:

```
http://localhost:8080
```

---

## 🔐 Authentication

Currently, APIs accept `userId` for simplicity.
The system is designed to be easily extended with **JWT-based authentication** and `/me/*` endpoints.

---

## 📌 Purpose

This project demonstrates:

* Proper backend layering
* SQL pagination correctness
* Business-driven design (not table-driven)
* Error handling & validation patterns used in production systems
