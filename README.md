# rest-users-api
A sample CRUD using Spring Rest API to manage users

# Installation

1. Clone this repository

```
git clone git@github.com:Identicum/rest-users-api.git
```

2. Compile the sources and run the application

```
cd rest-users-api
mvn clean spring-boot:run
```

# Rest Methods

* GET /users --> List all users
* GET /users/id --> Get information from given user
* POST /users --> Create new user
* PUT /users/id --> Update all data for given user id
* PATCH /users/id --> Update sent data for given user id
* POST /authenticate --> Authenticate user


# User attributes

* id: autonumeric
* username: String
* firstName: String
* lastName: String
* email: String


# Swagger access

API documented with Swagger. You can access to the UI on http://localhost:8081/swagger-ui.html
