🧮 My Building

A personal project with three goals: learning, continuous improvement, and connecting with people.
Living in an apartment, I noticed that yearly building budget meetings were frustrating. Costs kept rising, yet there were no clear metrics to track building management. This project asks a simple question:

  "How can we improve what we cannot measure?"

By tracking and analyzing building-related data, this application aims to provide actionable insights and help make better decisions.

🚀 Features

- Java-based architecture
- RESTful API with Spring Boot
- Track and analyze building management metrics

🛠️ Built With

- IntelliJ IDEA
- Java
- Spring Boot

🧪 Test
- Postman 

📈 Goal

- Have fun
- Learn Spring Boot
- Make building management transparent, measurable, and improv
- able through simple, clear metrics.

## 📁 Project Structure
```├── backend/                   # Java + Spring Boot backend
│   ├── src/
│   │   └── main/
│   │       └── java/pt/pedrorocha/mybuilding
│   │           ├── config/        # Application configuration classes
│   │           ├── controller/    # REST controllers
│   │           ├── dto/           # Data Transfer Objects
│   │           ├── entity/        # JPA entities
│   │           ├── exceptions/    # Custom exceptions
│   │           ├── mapper/        # Mapper classes
│   │           ├── model/         # Domain models
│   │           ├── repository/    # Spring Data repositories
│   │           └── services/      # Business logic / service classes
│   └── pom.xml                    # Maven project file
```

🔗 API Overview

The backend exposes a RESTful API to manage building data, including residents, budgets, and building metrics.
It allows clients (like the frontend) to create, read, update, and delete information, providing actionable insights for building management.

## 🔗 API Overview

The backend exposes a RESTful API to manage building data, including **building**, **company**,**residents**, **Client Group**, and **tickets**;
It allows clients to **create, read, update, and delete** information, providing actionable insights for building management.  

---
## 🐛 BUG LOG

| Object | Method | bug | Description | # | Status
|--------|---------|-------------------------|--------|---------------------|------------|
| Resident | DELETE | /residents endpoint fails when resident has associated tickets | Attempting to delete a resident via the DELETE /residents/{id} endpoint fails if there are tickets linked to the resident. | bugfix/001-resident-delete | 🔴 open |
---

## 📚 Endpoints

### Building
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/builings` | Retrive a list of all buildings |
| GET    | `/api/builings/{id}` | Retrieve details of a specific building |
| POST   | `/api/builings` | Retrive a list of all buildings |
| put    | `/api/residents/{id}` | Update building information |
| DELETE | `/api/buildings/{id}` | Remove a building |

### Company
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/companies` | Retrive a list of all companies |
| GET    | `/api/companies/{id}` | Retrieve details of a specific company |
| POST   | `/api/companies` | Retrive a list of all companies |
| put    | `/api/companies/{id}` | Update company information |
| DELETE | `/api/companies/{id}` | Remove a companies |

### Residents
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/residents` | Retrieve a list of all residents |
| GET    | `/api/residents/{id}` | Retrieve details of a specific resident |
| POST   | `/api/residents` | Add a new resident |
| PUT    | `/api/residents/{id}` | Update resident information |
| DELETE | `/api/residents/{id}` | Remove a resident |



## 🧠 Learning Goals

As this project is a personal learning journey, my main goals are:

Backend Development:
- Design and implement a RESTful API with Spring Boot
- Learn proper project structure and best practices in Java
- Work with DTOs, mappers, entities, repositories, and services

## 🔧 How to Run

1. Clone the repository:
   ```bash
   git clone git@github.com:PedroRocha84/android-java-profile.git

2. Open the project in Android Studio
3. Launch an emulator or connect a device
4. Click Run (Shift + F10)
