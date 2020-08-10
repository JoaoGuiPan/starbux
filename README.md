# Starbux
Starbux sample REST API

# Overview
This is a simple portfolio REST API I made to showcase a bit of my skills as well as being my personal coding playground! It's meant to be simple and easy to configure/run, so I made a few sacrifices, namely:
    
   - in-memory database
   - in-memory Basic Authentication
   - "hard-coded" promotions

# Requirements

   - JDK 11
   - Docker (optional)

# Build and run tests
Run

    ./mvnw clean install

# Deploy and run
Run 

    ./mvnw spring-boot:run

or

    docker build --tag starbux:1.0 ./
    docker run --publish 8080:8080 --name starbux starbux:1.0

# Documentarion
Access the API documentation at
    
    http://localhost:8080/swagger-ui.html

There are also some Postman files included in the project (a collection and 2 environment configs)
    
# Stage environment
Access the stage environment API at

    https://starbux.herokuapp.com

# Final considerations
Thank you for checking it out! I know it's not perfect, so please tell me what you think (`joaoguilhermepan@gmail.com`) or maybe open a pull request? Cheers!