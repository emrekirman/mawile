# Mawile Case Study #

## Getting Started ##

* The project was developed with Java 17. Therefore you recommend using Java 17
* PostgreSQL must be installed on the device to be run.
* A database named mawile_db must be created in PostgreSQL
* PostgreSQL information must be written correctly in the application.yml file.
* Once the above items are completed, you can start the project.



### I exported the sample insomnia collection and put it in the folder. If you download Insomnia and import the relevant yaml, you can test endpoints through the collection ###

Path ` /insomnia/mawileRequestCollection.yaml `

## Endpoints Curls ##

### Bank Account Create Request as curl ###

`
curl --request POST \
--url http://localhost:8080/api/v1/bank-accounts \
--header 'Content-Type: application/json' \
--data '{
"owner":"Eee"
}'
`
#### Account Number field is generated automatically  ####

### Bank Account Debit curl ###

`
curl --request POST \
--url http://localhost:8080/api/v1/bank-accounts/debit \
--header 'Content-Type: application/json' \
--data '{
"accountNumber":"1000003",
"amount":20.00
}'
`

### Bank Account Credit curl ###

`
curl --request POST \
--url http://localhost:8080/api/v1/bank-accounts/credit \
--header 'Content-Type: application/json' \
--data '{
"accountNumber":"1000003",
"amount":35.00
}'
`
### Bank Account Detail by accountNumber curl ###

`
curl --request GET \
--url http://localhost:8080/api/v1/bank-accounts/by-account-number/{AccountNumber} \
`

### Transaction Get All By AccountNumber Curl ###

`
curl --request GET \
--url http://localhost:8080/api/v1/transactions/by-bank-account-number/{AccountNumber} \
`


### Phone Bill Payment With Bank Account Curl ###

`
curl --request POST \
--url http://localhost:8080/api/v1/phone-bills/bank-account/payment \
--header 'Content-Type: application/json' \
--data '{
"phoneNumber":"1123123",
"operator":"VODAFONE",
"accountNumber":"{AccountNumber}",
"amount":30
}'
`
