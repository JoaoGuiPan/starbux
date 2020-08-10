# Starbux
Starbux sample API

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
