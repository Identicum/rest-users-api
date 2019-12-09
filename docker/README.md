# rest-users-api
A sample CRUD using Spring Rest API to manage users

## Source
Source code can be found at: https://github.com/Identicum/rest-users-api

## Usage

### Install

Build `identicum/rest-users-api` from source:

    docker build -t identicum/rest-users-api .

### Run the container

Run the image, binding associated ports, and defining your custom variables as environment variables:

    docker run -d \
      --name rest-users-api \
      --restart=always \
      -p 8081:8081 \
      identicum/rest-users-api
