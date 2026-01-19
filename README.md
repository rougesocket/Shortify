# 🔗 Shortify — URL Shortener

**Shortify** is a full‑stack URL shortening application built with **Spring Boot and Redis**.  
It provides secure **Google OAuth2 authentication**, fast URL shortening and redirection, and per‑user link analytics.

---

## 🚀 Features

✅ Shorten long URLs into compact short links  
✅ **Google OAuth2 login** for secure user authentication  
✅ Store and manage user‑specific URLs  
✅ Instant redirection for shortened URLs  
✅ Analytics per user (click counts & history)  
✅ Built with scalable, modern tech (Spring Boot + Redis)  

---

## 🧠 Tech Stack

| Layer | Technologies |
|-------|--------------|
| Backend | Java 17, Spring Boot |
| Authentication | Spring Security, OAuth2 (Google) |
| Storage / Cache | Redis |
| Build & Dev | Maven, Git |

---

## 📄 Architecture Overview

1. **User Authentication**  
   Users sign in via **Google OAuth2**. After successful login, user sessions are managed securely.

2. **Short URL Creation**  
   Authenticated users can submit long URLs via API (or frontend). The backend generates a unique key and stores it in Redis with metadata such as owner and timestamps.

3. **Redirection**  
   When a short link is accessed, the service looks up the key in Redis and redirects the visitor to the original URL.

4. **Analytics**  
   Each user can view their own shortened URLs and basic click data (e.g., count and timestamps).

---

## 🛠️ How It Works

- The service **maps** long URLs to unique short keys.
- **Redis** provides fast key/value lookups for redirection.
- API endpoints handle creation, listing, and redirection logic.
- Security is enforced via **OAuth2 login with Google**.

---

## 📌 Getting Started

### Prerequisites

Make sure you have:

- Java 17
- Maven
- Redis running locally or remotely
- Google OAuth2 credentials (Client ID & Secret)

---

### Setup

1. **Clone the repository**

    ```bash
    git clone https://github.com/rougesocket/Shortify.git
    cd Shortify
    ```

2. **Configure environment variables**

    Create a `.env` or set the following in `application.properties` / `application.yml`:

    ```properties
    spring.redis.host=<YOUR_REDIS_HOST>
    spring.redis.port=<YOUR_REDIS_PORT>

    spring.security.oauth2.client.registration.google.client-id=<GOOGLE_CLIENT_ID>
    spring.security.oauth2.client.registration.google.client-secret=<GOOGLE_CLIENT_SECRET>
    ```

3. **Build & run**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
---
