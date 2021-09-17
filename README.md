# rest-users-api
A sample CRUD using Spring Rest API to manage users

# Installation

- Clone this repository

```bash
git clone git@github.com:Identicum/rest-users-api.git
```

- Compile the sources and run the application

```bash
cd rest-users-api
mvn clean spring-boot:run
```

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

API documented with Swagger. You can access to the UI on http://localhost:8081/swagger-ui.html



# Identicum DevOps and Cloud Deployment

Docker and Kubernetes DevOps artifacts for Spring API

## Contents

* `/` -  Contains the Dockerfiles for API.
* `docker-mysql/` -  Contains a docker-compose example for the API and mysql repository.
* `helm/` - Helm charts.
* `k8s/` - Kubernetes artifacts.
* `skaffold.yml` - is used by the Skaffold CLI.


## Sample
* Knowledge of Kubernetes, Skaffold or Helm is assumed.

### Run the container

Run the image, binding associated ports, and defining your custom variables as environment variables:

    docker run -d \
      --name rest-users-api \
      --restart=always \
      -p 8081:8081 \
      identicum/rest-users-api

### Helm

For helm deployment please see [here](helm/).

### Skaffold

- `skaffold.yml` is used by the Skaffold CLI
- `k8s/` k8s artifacts are used for deployment into the K8s cluster and they are integrated into the `skaffold.yml` for continuous deployment
- `skaffold build` is used for building docker images from `skaffold.yml`
- `skaffold run` is used for building and deploying docker images on to a Kubernetes cluster
- `skaffold dev` is used to watch for files and trigger build and deployment based on that


#### Sample Skaffold

* This assumes minikube is running and skaffold and kubectl are installed.

```
# Get your minikube ip
minikube ip

# You can put DNS entries in an entry in /etc/hosts. For example:
# 192.168.99.100 api.example.identicum.com


# If you do not want to use the 'default' namespace, set your namespace using:
kubectl config set-context $(kubectl config current-context) --namespace=<insert-namespace-name-here>

# Run Skaffold to build Docker images and deploy
skaffold dev

In a separate terminal tab or window, run the kubectl get pods command to monitor status of the deployment. Wait until all the pods are ready. 

Then open https://api.example.identicum.com/users
```