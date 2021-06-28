# itemeligibility

This project provides an API to check whether an item with preset set of conditions is eligible for a new programm or not 

# API for checking the eligibility 

GET http://localhost:8080/itemeligibility/items/eligibilityCheck

Sample Request :

{
    "title":"sampleBook2",
    "category":1,
    "sellerName":"Seller BGd",
    "price":100.00
}

Sample Response :

{
    "data": {
        "eligibile": true,
        "title": "sampleBook2"
    },
    "status": "OK"
}

# Prerequisites

The servise uses mongo db to store the required admin data in two collections 

1.category 
2.seller

The service also provides a set of admin APIs to add new categories and sellers and to change their approval and enrollment status following are the admin APIs 

For Category 

POST http://localhost:8080/itemeligibility/categories/saveCategory

Sample Request :

{
    "category":4,
    "categoryName":"Applainces",
    "preApproved":false 
}

Sample Response :

{
    "data": "60d7f6ee22fafe7f769ac10b",
    "status": "OK"
}

PUT http://localhost:8080/itemeligibility/categories/editApproval/60d7e1515e45f76a3748fdc8?preApproved=<true,false>

Sample Response :

{
    "data": "60d7f6ee22fafe7f769ac10b",
    "status": "OK"
}

For Seller :

POST http://localhost:8080/itemeligibility/sellers/saveSeller

Sample request :

{
    "name":"Seller DAB",
    "enrolled":false 
}

Sample response :

{
    "data": "60d7f6ee22fafe7f769ac10b",
    "status": "OK"
}


PUT http://localhost:8080/itemeligibility/sellers/enrollSeller/60d7f6ee22fafe7f769ac10b?enrolled=<true,false>

Sample Response :

{
    "data": "60d7f6ee22fafe7f769ac10b",
    "status": "OK"
}


#Basic commands for the service abnd changes to property file 

The service uses some properties including the connection string properties for Mongo 

spring.data.mongodb.host=localhost
spring.data.mongodb.port=4001
spring.data.mongodb.database=items

and an additional property 

minimum.price = <Minimum price>
    
Commands to start the application 
    
mvn spring-boot:run


