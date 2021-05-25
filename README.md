# Spring Boot, MSSQL, JPA, Hibernate Rest API

Build Restful CRUD API for a simple Note-Taking application using Spring Boot, MSsql, JPA and Hibernate.
* Recommended software architecture: Representational state transfer (REST)

## The Problem
Develop a note-taking API for creating, reading, editing and deleting notes.
Each note should keep the following information:
* Title
* Description
* Creation Date
* Modification Date
* Expiration Date 
* Owner


## Requirements

1. Java - 1.8.x
2. Maven - 3.x.x
3. MS sql - 5.x.x



## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/jyotirmani/note-taking-API.git
```

**2.a Create MS sql database**
```bash
create database notes_app
```

**2.b Using a Mysql database
1. Create Mysql database

create database notes_app
2. Change mysql username and password as per your installation

open src/main/resources/application.properties

uncomment Mysql properties and comment h2 properties

change spring.datasource.username and spring.datasource.password as per your mysql installation


**3. Change mssql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mssql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/easy-notes-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/notes
    
    POST /api/notes
    
    GET /api/notes/{noteId}
    
    PUT /api/notes/{noteId}
    
    DELETE /api/notes/{noteId}

You can test them using postman or any other rest client.


The app will start running at http://localhost:8080

You can test them using the embedded Swagger UI.

