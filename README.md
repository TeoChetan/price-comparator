#  Discount Features API

A Spring Boot + PostgreSQL backend for comparing product prices, tracking discounts, optimizing shopping baskets, and setting custom price alerts.

---

## 📚 Features

* 🔍 **Best Discounts** – View the top current deals
* 🔁 **New Discounts** – Track newly added promotions
* 📊 **Price History** – Filterable by store, category, brand
* ♻️ **Product Substitutes** – Compare unit prices across products
* 🧼 **Basket Cost Estimator** – Simulate lowest cost across stores
* 📦 **Store Basket Optimizer** – Group by store for smarter shopping
* 🔔 **Price Alerts** – Set alerts and get notified when prices drop

---

## 🚀 Tech Stack

* Java 17
* Spring Boot 3
* Maven
* PostgreSQL
* Lombok
* RESTful APIs

---

## ⚙️ Setup

### 1. Clone the repo

```bash
git clone https://github.com/your-username/price-comparator.git
cd price-comparator
```

### 2. Configure database

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/price_comparator
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

> ✅ You must have PostgreSQL installed and the `price_comparator` database created.

### 3. Run the app

```bash
./mvnw spring-boot:run
```

---

## 📁 Data Import

Place your `.csv` data in:

```
src/main/resources/data/price/
src/main/resources/data/discount/
```

### ✅ Format Requirements

* **Product File:** `store_yyyy-MM-dd.csv`
* **Discount File:** `store_category_date.csv`

The app reads and imports these on startup.

---

## 🔗 API Endpoints

### 🏷️ Discounts

| Method | Endpoint              | Description                        |
| ------ | --------------------- | ---------------------------------- |
| GET    | `/api/discounts/best` | Top 10 discounts currently active  |
| GET    | `/api/discounts/new`  | Discounts added in the last 24 hrs |

### 📈 Products

| Method | Endpoint                                | Description                   |
| ------ | --------------------------------------- | ----------------------------- |
| GET    | `/api/products/history/{productId}`     | View price trend of a product |
| GET    | `/api/products/substitutes/{productId}` | Compare value per unit        |

### 🧼 Basket

| Method | Endpoint               | Description                            |
| ------ | ---------------------- | -------------------------------------- |
| POST   | `/api/basket/optimize` | Cheapest products per store            |
| POST   | `/api/basket/cost`     | Best combo basket from multiple stores |

### 🔔 Price Alerts

| Method | Endpoint                | Description                |
| ------ | ----------------------- | -------------------------- |
| POST   | `/api/alerts`           | Set alert for target price |
| GET    | `/api/alerts/triggered` | View all triggered alerts  |

---
## 👨‍💼 Author

**Daniel-Adrian-Teo Chetan**

📧 teochetan1@gmail.com
