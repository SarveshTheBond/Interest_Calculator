# Interest Calculator
BestDeals Bank (Hypothetical) wants to build a Interest calculator. User can create an account, get daily end-of-day messages, request to close an account and get summary of total interest procured on an account.

### There are 4 main API:
* Create Account: This is a Post call that creates new Account into the System
* Save Balance: This API is used to get end-of-day messages in json form. This api post receiving the message calculate the Simple Interest for that day in form of epoch time.
* Get Account Details: This API displays interest procured on an account for each day. Here the Interest date is not in sorted order.
* Close Account: When any account needs to be closed then this API is called. This will change the Account status from "Active" to "Closed". Post this, API call to "Get Account Details" needs to me made to get all the interest calculated till date.


#### Technology Stack

* Spring Boot
* Swagger UI
* Rest
* Java 8
* Git

### Clone the repository at your local machine.

```
$ git clone https://github.com/SarveshTheBond/Interest_Calculator.git
```

### How to run the `interest_calculator`


```
$ cd returns_calculator
$ mvn spring-boot:run

```

### Below are few assumptions we have mad while designing these API's:
* Simple Interest Rate of 10% per annum.
* Account Open date and Account present in end-of-day messages are not older than month.
* For saving Transactional Data I have used Concurrent HashMap. With this the limitation is that in case of server restart the existing value is lost.
* Account Closure date will always be greater than Account Creation date and also no EOD report date.
* Post Account Closure call still the end-of-day messages can have this account no. in list but since this account status is closed now hence no interest will be calculated on this account.
* In our Test cases we are checking if successfule call to API is made

### For Interest Calculator Rest API, Please click on the link below,

* [ReturnsCalculator](http://localhost:8080/swagger-ui.html).

![Swagger UI](/src/main/resources/images/Swagger_UI.png "Swagger UI")
![Swagger UI](/src/main/resources/images/Create_Account.png "Create Account")
![Swagger UI](/src/main/resources/images/Save_Balance.png "Save Balance")
![Swagger UI](/src/main/resources/images/Get_Account.png "Get Account Details")
![Swagger UI](/src/main/resources/images/Close_Account.png "Close Account")

