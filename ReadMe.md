# URLShortner

A simple URL shortener built using Spring Boot.

---

## 🛠️ Tech Stack

- Java 23
- Spring Boot
- Spring Web (MVC)
- Spring Data JPA
- Spring Security
- Thymeleaf
- PostgreSQL
- Maven

---

## 🌐 Accessible Endpoints

| Endpoint | Method | Description |
|--------|--------|-------------|
| `/` | GET | Home page with URL shortening form |
| `/shortenURL` | POST | Generates short URL from long URL |
| `/{shortURL}` | GET | Redirects to original long URL |
| `/login` | GET | User login page |
| `/register` | GET | User registration page |

---

## 🗄️ Database

- **PostgreSQL** is used for persistent storage.

---
