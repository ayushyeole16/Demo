# Postman Test Cases for Product CRUD Operations and Authentication

## Prerequisites
- Start the Spring Boot application on port 8081.
- Use Postman to test the endpoints.
- For protected endpoints, obtain a valid JWT token by logging in via the `/api/auth/login` endpoint.
- Use the token in the `Authorization` header for all protected endpoints:
  ```
  Authorization: Bearer <your_jwt_token>
  ```

## Authentication Endpoints

### 1. Register New User

- **Method:** POST
- **URL:** `http://localhost:8081/api/auth/register`
- **Headers:**
  - Content-Type: application/json
- **Body (raw JSON):**
```json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123",
  "roles": ["CUSTOMER"]
}
```
- **Expected Response:** 200 OK with message "User registered successfully".
- **Notes:** Roles are optional; defaults to ["CUSTOMER"] if not provided.

### 2. Login User

- **Method:** POST
- **URL:** `http://localhost:8081/api/auth/login`
- **Headers:**
  - Content-Type: application/json
- **Body (raw JSON):**
```json
{
  "usernameOrEmail": "testuser",
  "password": "password123"
}
```
- **Expected Response:** 200 OK with JWT token in JSON format:
```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9...",
  "tokenType": "Bearer"
}
```
- **Notes:** Can login with username or email. Save the token for subsequent requests.

---

## 1. Add New Product (Create)

- **Method:** POST  
- **URL:** `http://localhost:8081/api/products`  
- **Headers:**  
  - Content-Type: application/json  
  - Authorization: Bearer <token>  
- **Body (raw JSON):**
```json
{
  "name": "Sample Product",
  "description": "This is a sample product",
  "price": 19.99,
  "stock": 100,
  "category": "Electronics"
}
```
- **Expected Response:** 200 OK with created product JSON including generated ID.

---

## 2. Get All Products (Read)

- **Method:** GET  
- **URL:** `http://localhost:8081/api/products`  
- **Headers:**  
  - Authorization: Bearer <token>  
- **Expected Response:** 200 OK with JSON array of products.

---

## 3. Get Product by ID (Read)

- **Method:** GET  
- **URL:** `http://localhost:8081/api/products/{id}`  
- **Headers:**  
  - Authorization: Bearer <token>  
- **Expected Response:** 200 OK with product JSON if found, 404 if not found.

---

## 4. Update Product (Update)

- **Method:** PUT  
- **URL:** `http://localhost:8081/api/products/{id}`  
- **Headers:**  
  - Content-Type: application/json  
  - Authorization: Bearer <token>  
- **Body (raw JSON):**
```json
{
  "name": "Updated Product",
  "description": "Updated description",
  "price": 29.99,
  "stock": 50,
  "category": "Electronics"
}
```
- **Expected Response:** 200 OK with updated product JSON.

---

## 5. Delete Product (Delete)

- **Method:** DELETE  
- **URL:** `http://localhost:8081/api/products/{id}`  
- **Headers:**  
  - Authorization: Bearer <token>  
- **Expected Response:** 200 OK or 204 No Content.

---

## Notes

- Replace `{id}` with actual product ID.
- Ensure JWT token is valid and included in Authorization header.
- If unauthorized, check login and token issuance.
- Use Postman environment variables to store token for convenience.
