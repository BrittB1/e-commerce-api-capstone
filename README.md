# 🎮 Video Game Store - E-Commerce API

A full-stack e-commerce application built with Spring Boot, MySQL, and JavaScript. This project implements a RESTful API for a video game store with user authentication, product management, and shopping cart functionality.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Testing](#testing)
- [Screenshots](#screenshots)
- [Lessons Learned](#lessons-learned)
- [Future Enhancements](#future-enhancements)
- [Author](#author)

---

## 🎯 Overview

This project is the capstone for the Learn To Code Academy Java Focus Program. It demonstrates proficiency in:

- Building RESTful APIs with Spring Boot
- Database design and JDBC operations with MySQL
- User authentication with JWT tokens
- Role-based access control (User vs Admin)
- Shopping cart functionality
- Bug identification and resolution

---

## ✨ Features

### Phase 1: Categories Controller
- ✅ GET all categories (public)
- ✅ GET category by ID (public)
- ✅ POST create category (admin only)
- ✅ PUT update category (admin only)
- ✅ DELETE category (admin only)

### Phase 2: Bug Fixes
- ✅ Fixed product search functionality (price filters)
- ✅ Fixed product update creating duplicates

### Phase 3: Shopping Cart
- ✅ GET user's shopping cart
- ✅ POST add product to cart
- ✅ PUT update product quantity
- ✅ DELETE clear entire cart

### Authentication
- ✅ User registration
- ✅ User login with JWT tokens
- ✅ Role-based authorization (USER, ADMIN)

---

## 🛠 Tech Stack

### Backend
| Technology | Purpose |
|------------|---------|
| Java 17 | Programming language |
| Spring Boot 2.7 | Application framework |
| Spring Security | Authentication & authorization |
| JWT | Token-based authentication |
| JDBC | Database connectivity |
| Maven | Dependency management |

### Database
| Technology | Purpose |
|------------|---------|
| MySQL 8.0 | Relational database |
| MySQL Workbench | Database management GUI |

### Testing
| Technology | Purpose |
|------------|---------|
| Insomnia | API testing |
| JUnit | Unit testing |

### Frontend (Pre-built)
| Technology | Purpose |
|------------|---------|
| HTML/CSS | Structure and styling |
| JavaScript | API interactions |
| Axios | HTTP requests |

---

## 📁 Project Structure

```
e-commerce-api-capstone/
├── capstone-api-starter/              # Backend API
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/org/yearup/
│   │   │   │   ├── controllers/       # REST Controllers
│   │   │   │   │   ├── AuthenticationController.java
│   │   │   │   │   ├── CategoriesController.java
│   │   │   │   │   ├── ProductsController.java
│   │   │   │   │   └── ShoppingCartController.java
│   │   │   │   ├── data/              # Data Access Layer
│   │   │   │   │   ├── mysql/         # MySQL implementations
│   │   │   │   │   │   ├── MySqlCategoryDao.java
│   │   │   │   │   │   ├── MySqlProductDao.java
│   │   │   │   │   │   └── MySqlShoppingCartDao.java
│   │   │   │   │   ├── CategoryDao.java
│   │   │   │   │   ├── ProductDao.java
│   │   │   │   │   └── ShoppingCartDao.java
│   │   │   │   ├── models/            # Data models
│   │   │   │   │   ├── Category.java
│   │   │   │   │   ├── Product.java
│   │   │   │   │   ├── ShoppingCart.java
│   │   │   │   │   └── User.java
│   │   │   │   └── security/          # JWT configuration
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/                      # Unit tests
│   ├── database/                      # SQL scripts
│   └── pom.xml
├── capstone-web-applications/         # Frontend
│   └── capstone-client-videogamestore/
│       ├── css/
│       ├── images/
│       ├── js/
│       └── index.html
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- MySQL 8.0
- Maven
- IntelliJ IDEA (recommended)
- Insomnia or Postman (for API testing)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/BrittB1/e-commerce-api-capstone.git
   cd e-commerce-api-capstone
   ```

2. **Set up the database**
    - Open MySQL Workbench
    - Run the SQL script in `capstone-api-starter/database/` to create the `videogamestore` database

3. **Configure database connection**
    - Open `capstone-api-starter/src/main/resources/application.properties`
    - Update with your MySQL credentials:
      ```properties
      datasource.url=jdbc:mysql://localhost:3306/videogamestore
      datasource.username=root
      datasource.password=YOUR_PASSWORD
      ```

4. **Run the application**
    - Open the project in IntelliJ
    - Run `EasyshopApplication.java`
    - The API will start at `http://localhost:8080`

5. **Run the frontend**
    - Open `capstone-web-applications/capstone-client-videogamestore` in IntelliJ
    - Open `index.html` and click the browser icon to launch

---

## 🔌 API Endpoints

### Authentication

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/login` | Login and get JWT token | Public |
| POST | `/register` | Register new user | Public |

### Categories

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/categories` | Get all categories | Public |
| GET | `/categories/{id}` | Get category by ID | Public |
| POST | `/categories` | Create new category | Admin |
| PUT | `/categories/{id}` | Update category | Admin |
| DELETE | `/categories/{id}` | Delete category | Admin |

### Products

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/products` | Get all products (with filters) | Public |
| GET | `/products/{id}` | Get product by ID | Public |
| POST | `/products` | Create new product | Admin |
| PUT | `/products/{id}` | Update product | Admin |
| DELETE | `/products/{id}` | Delete product | Admin |

**Product Search Parameters:**
- `cat` - Filter by category ID
- `minPrice` - Minimum price
- `maxPrice` - Maximum price
- `subCategory` - Filter by subcategory

Example: `GET /products?cat=1&minPrice=25&maxPrice=100`

### Shopping Cart

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/cart` | Get user's cart | Authenticated |
| POST | `/cart/products/{id}` | Add product to cart | Authenticated |
| PUT | `/cart/products/{id}` | Update quantity | Authenticated |
| DELETE | `/cart` | Clear cart | Authenticated |

---

## 🗃 Database Schema

### Tables

**categories**
| Column | Type | Description |
|--------|------|-------------|
| category_id | INT | Primary key, auto-increment |
| name | VARCHAR(50) | Category name |
| description | VARCHAR(255) | Category description |

**products**
| Column | Type | Description |
|--------|------|-------------|
| product_id | INT | Primary key, auto-increment |
| name | VARCHAR(100) | Product name |
| price | DECIMAL(10,2) | Product price |
| category_id | INT | Foreign key to categories |
| description | TEXT | Product description |
| subcategory | VARCHAR(50) | Product subcategory |
| stock | INT | Available stock |
| featured | BOOLEAN | Featured product flag |
| image_url | VARCHAR(255) | Product image URL |

**shopping_cart**
| Column | Type | Description |
|--------|------|-------------|
| user_id | INT | Foreign key to users |
| product_id | INT | Foreign key to products |
| quantity | INT | Quantity in cart |

---

## 🧪 Testing

### Using Insomnia

1. Import the collection from `capstone_insomnia_collections.yaml`
2. Set up environment variable: `baseUrl = http://localhost:8080`
3. Run tests in order

### Sample Test Requests

**Login:**
```json
POST http://localhost:8080/login
Content-Type: application/json

{
    "username": "admin",
    "password": "password"
}
```

**Add to Cart:**
```json
POST http://localhost:8080/cart/products/1
Authorization: Bearer <your_token>
```

---

## 📸 Screenshots

*Coming soon*

---

## 📚 Lessons Learned

### Technical Skills
- Building RESTful APIs with proper HTTP methods and status codes
- Implementing JWT authentication and role-based authorization
- Writing SQL queries with prepared statements to prevent SQL injection
- Debugging and fixing production bugs

### Key Concepts Mastered

**Annotations:**
- `@RestController`, `@RequestMapping`, `@CrossOrigin`
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- `@PathVariable`, `@RequestBody`, `@Autowired`
- `@PreAuthorize` for security

**HTTP Status Codes:**
- 200 OK, 201 Created, 204 No Content
- 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found

**Architecture:**
- Controller → DAO → Database pattern
- Interface-based programming for loose coupling
- Dependency injection with Spring

### Bugs Fixed
1. **Search Bug:** Price filter used wrong comparison operator (`<=` instead of `>=`)
2. **Update Bug:** Product update called `create()` instead of `update()`

---

## 🔮 Future Enhancements

- [ ] Implement checkout functionality
- [ ] Add order history
- [ ] User profile management
- [ ] Product reviews and ratings
- [ ] Wishlist feature
- [ ] Admin dashboard
- [ ] Email notifications

---

## 👤 Author

**Britt**

- GitHub: [@BrittB1](https://github.com/BrittB1)
- Project: [E-Commerce API Capstone](https://github.com/BrittB1/e-commerce-api-capstone)

---

## 🙏 Acknowledgments

- Learn To Code Academy - Java Focus Program
- Instructors and mentors
- Claude AI for guided learning assistance

---

## 📄 License

This project is for educational purposes as part of the Learn To Code Academy curriculum.

---

*Built with ❤️ and ☕ during the December 2025 cohort*