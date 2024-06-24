# rest-users-api
A sample CRUD using Spring Rest API to manage users


# Protecting the API

Since it was designed for testing purposes the API is public by default. 
You can protect the API with spring profiles:


## Basic Authentication

Edit default username and password in the [application-basic.properties](src/main/resources/application-basic.properties) file 
and run the application as follows:

```bash
cd rest-users-api
mvn clean spring-boot:run -spring.profiles.active=basic
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

API documented with Swagger. You can access to the UI on http://localhost:8080/swagger-ui.html
