# 💰 MoneyMind — Intelligent Financial Analysis Platform

MoneyMind is a full-stack financial intelligence platform designed to analyze personal expenses, automatically detect subscriptions, and generate actionable financial insights.

The system acts as a **financial copilot**, helping users understand spending behavior, identify unnecessary subscriptions, and improve financial health.

---

## 🚀 Features

### ✅ Expense Management

* Transaction registration
* Automatic categorization
* Multi-account support
* Historical tracking

### 🤖 Subscription Detection Engine

* Automatic recurring payment detection
* Billing cycle identification
* Price variation monitoring
* Forgotten subscription alerts

### 📊 Financial Analytics

* Monthly spending analysis
* Category distribution
* Financial Health Score
* Smart spending insights

### 🚨 Smart Alerts

* Subscription price increase detection
* Overspending alerts
* Savings opportunities

---

## 🧠 Architecture

The project follows **Clean Architecture + Domain Driven Design (DDD)** principles.

```
Presentation Layer
        ↓
Application Layer (Use Cases)
        ↓
Domain Layer (Business Rules)
        ↓
Infrastructure Layer
```

Modules are organized by business domains:

* Auth
* User
* Transaction
* Subscription
* Analytics
* Notification

---

## 🛠 Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* JPA / Hibernate
* PostgreSQL
* Docker

### Frontend

* React
* TailwindCSS
* Chart.js

---

## ⚙️ Running Locally

### Requirements

* Java 17+
* Docker
* Docker Compose

### Start infrastructure

```
docker-compose up -d
```

### Run application

```
./mvnw spring-boot:run
```

Application available at:

```
http://localhost:8080
```

---

## 📈 Roadmap

* [ ] Subscription Detection Engine
* [ ] Financial Insights Engine
* [ ] Async Processing
* [ ] Real-time Dashboard
* [ ] Machine Learning Predictions
* [ ] Multi-tenant SaaS support

---

## 🧪 Testing

```
./mvnw test
```

---

## 🧩 Future Improvements

* Open Finance integration
* Predictive expense analysis
* Distributed processing
* Microservices architecture

---

## 👨‍💻 Author

Backend Engineer focused on scalable systems, financial analytics and high-performance Java applications.

---

## 📄 License

MIT License
