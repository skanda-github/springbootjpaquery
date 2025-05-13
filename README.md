# springbootjpaquery

---

This project demonstrates how to write custom queries using `@Query` annotation and JPQL in Spring Boot with a simple `User` entity. You'll learn how to:

* Write JPQL queries
* Use wildcard and case-insensitive searches
* Query with dynamic parameters

---

## 🚀 API Testing (Using Postman, curl, or `.http` client)

Below are the endpoints you can use to test the functionality.

---

### ✅ 1. Create User

* **Method:** `POST`
* **URL:** `http://localhost:8080/api/users`
* **Body:**

```json
{
  "name": "Alice",
  "email": "alice@example.com",
  "age": 28
}
```

#### 🔍 What it does:

* Saves a new user to the in-memory H2 database.
* Uses Spring Data JPA's built-in `save()` method in `UserRepository`.
* No custom query is used here—this is standard repository usage.

---

### ✅ 2. Get User by Email

* **Method:** `GET`
* **URL:** `http://localhost:8080/api/users/email/alice@example.com`

#### 🔍 What it does:

* Fetches a user whose email matches exactly.

#### 📜 JPQL Query Used:

```java
@Query("SELECT u FROM User u WHERE u.email = ?1")
User findUserByEmail(String email);
```

#### 🧠 Explanation:

* JPQL works on the **`User` entity** (not the database table `users`).
* `?1` is the first method parameter (`email`).
* Returns a single `User` object if found.

---

### ✅ 3. Search Users by Name (partial match, case-insensitive)

* **Method:** `GET`
* **URL:** `http://localhost:8080/api/users/search?keyword=ali`

#### 🔍 What it does:

* Searches for users whose **name contains** `"ali"` (e.g., `"Alice"`, `"Alison"`, `"Malik"`).
* The search is case-insensitive.

#### 📜 JPQL Query Used:

```java
@Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
List<User> searchByNameContaining(String keyword);
```

#### 🧠 Explanation:

* `LOWER(u.name)` converts the name to lowercase to ensure **case-insensitive comparison**.
* `LIKE` is used for partial matching.
* `CONCAT('%', ?1, '%')` builds the pattern:

  * `%ali%` matches `"Alice"`, `"Alison"`, `"Malik"`, etc.

---

### ✅ 4. Get Users Older Than a Certain Age

* **Method:** `GET`
* **URL:** `http://localhost:8080/api/users/older-than/25`

#### 🔍 What it does:

* Returns a list of users whose `age > 25`.

#### 📜 JPQL Query Used:

```java
@Query("SELECT u FROM User u WHERE u.age > ?1")
List<User> findUsersOlderThan(int age);
```

#### 🧠 Explanation:

* Compares the `age` field of the `User` entity using the `>` operator.
* Returns all users older than the given age (e.g., 26, 35, etc.).