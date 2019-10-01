# micronaut-transferex-api

RestFul money transfer service uses following Technologies:

* Micronaut
* Kotlin
* Hexagonal Arch
* CQRS
* Jpa
* H2 DB

## Build

`mvn clean install`

## Run 

`java -jar target/micronaut-transferex-api-1.0.0.jar`

## Tests

mvn test



Usage
-----

By default, the API is accessible on port 6161. 
The following requests are supported:

- POST /accounts - create new account request. 
Request body example:

```json 
{
	"owner":"Enes Açıkoğlu",
	"balance":1200.50
}
``` 

- GET /accounts/2 - account by id

- GET /accounts - gets all accounts

- GET /transactions/2 transaction by id

- GET /transactions gets all transactions

- POST /transfers - transfers between accounts. 
Request body example:

```json 
{
	"fromAccount":1,
	"toAccount":2,
	"amount":100.50
}
``` 