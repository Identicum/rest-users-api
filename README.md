# users-rest-api
A sample CRUD using Spring Rest API to manage users

# Installation

1. Clone this repository

```
git clone git@github.com:Identicum/users-rest-api.git
```

2. Compile the sources

```
cd users-rest-api
mvn clean package
```

3. Run the application

```
java -jar target/users-rest-api-0.1.0.jar
```

# Rest Methods

* GET /users --> List all users
* GET /users/id --> Get information from given user
* POST /users --> Create new user
* PUT /users/id --> Update data for given user id

# User attributes

* id: autonumeric
* username: String
* firstName: String
* lastName: String
