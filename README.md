# TOT_Phones

Test project. A phone-book-like web-application connected with postgres Database. 
CRUD functionale and validation for all the operations are implemented.
Service class methods tested with unit tests.
DAO package has to DataAccess java classes, one for postgres database implementation, 
one for in-memory database implementation (Testing purposes).

Default application.yml settings are as follows:
      port: 5432
      username: postgres
      password: password
      host: localhost
      databaseName: clients
