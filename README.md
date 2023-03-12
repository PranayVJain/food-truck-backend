# food-truck-backend
Lunch is important and knowing our lunch choices is even more so this service helps users to search
for all the available lunch options provided by food trucks. It allows the admin to add and edit the
 food trucks while the users can list the food trucks on any given day. 

## REST API

### Add a new food truck
    POST : /v1/foodtrucks
#### Request
    curl --location --request POST 'http://localhost:8080/v1/foodtrucks' \
        --header 'Content-Type: application/json' \
        --data-raw '{
            "name": "FoodTruck",
            "description": "A food truck",
            "availableDate": "2023-03-11T06:39:29.21Z"
        }'
#### Response
    Status: 201 OK
    {
        "id": "0231a03b-ff9a-493a-82d2-0e72064a3a63",
        "name": "FoodTruck",
        "description": "A food truck",
        "availableDate": "2023-03-11T06:39:29.21"
    }

### Get all food trucks
    GET : /v1/foodtrucks
#### Request
    curl --location --request GET 'http://localhost:8080/v1/foodtrucks'
#### Response
    Status: 200 OK
    [
        {
            "id": "0231a03b-ff9a-493a-82d2-0e72064a3a63",
            "name": "FoodTruck",
            "description": "A food truck",
            "availableDate": "2023-03-11T06:39:29.21"
        }
    ]

### Get all food trucks within given time range
    GET : /v1/foodtrucks?fromDate={fromDate}&toDate={toDate}
#### Request
    curl --location --request GET 'http://localhost:8080/v1/foodtrucks?fromDate=2023-03-09T00:39:29&toDate=2023-03-12T00:39:29'
#### Response
    Status: 200 OK
    [
        {
            "id": "0231a03b-ff9a-493a-82d2-0e72064a3a63",
            "name": "FoodTruck",
            "description": "A food truck",
            "availableDate": "2023-03-11T06:39:29.21"
        }
    ] 

### Update food truck
    PUT : /v1/foodtrucks/{foodtruckid}
#### Request
    curl --location --request PUT 'http://localhost:8080/v1/foodtrucks/0231a03b-ff9a-493a-82d2-0e72064a3a63' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "FoodTruck2"
    }'
#### Response
    Status: 200 OK


## Running food-truck-backend locally
food-truck-backend is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built 
using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the 
command line (it should work well with Java 11 or newer):

```
git clone https://github.com/PranayVJain/food-truck-backend.git
cd food-truck-backend
./mvnw package
java -jar target/*.jar
```

You can then access the apis food-truck-backend at http://localhost:8080/

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this, it will 
pick up changes that you make in the project immediately (changes to Java source files require a 
compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

## Building a Container

There is no `Dockerfile` in this project. You can build a container image (if you have a docker 
daemon) using the Spring Boot build plugin:

```
./mvnw spring-boot:build-image
```

## Database configuration

In its default configuration, food-truck-backend uses an in-memory database (H2) which
gets created at startup along with the tables. The h2 console is exposed at `http://localhost:8080/h2-console`,
and it is possible to inspect the content of the database using the `jdbc:h2:mem:testdb` url.

## Working with food-truck-backend in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 11 or newer (full JDK, not a JRE).
* [git command line tool](https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
  not there, follow the install process [here](https://www.eclipse.org/m2e/)
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * [IntelliJ IDEA](https://www.jetbrains.com/idea/)
  * [VS Code](https://code.visualstudio.com)

### Steps:

1) On the command line run:
    ```
    git clone https://github.com/PranayVJain/food-truck-backend.git
    ```
2) Inside Eclipse or STS:
    ```
    File -> Import -> Maven -> Existing Maven project
    ```

    Then either build on the command line `./mvnw generate-resources` or use the Eclipse launcher (right click on project and `Run As -> Maven install`). 
    Run the application main method by right-clicking on it and choosing `Run As -> Java Application`.

3) Inside IntelliJ IDEA
    In the main menu, choose `File -> Open` and select the food-truck-backend [pom.xml](pom.xml). Click on the `Open` button.
    Run the application by right-clicking on the `FoodTruckApplication` main class and choosing `Run 'FoodTruckApplication'`.

4) food-truck-backend API end points

    Use [http://localhost:8080](http://localhost:8080) to access APIs.
