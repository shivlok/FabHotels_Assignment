#Fabhotels_Assignment

  ## Problem Statement:
  Create a User Wallet system where a user can come and register for a wallet. Users should
  be able to make transactions using their wallets
  
  
  ###Basic requirements
  
  1 Create a user account - Sign-in/Sign-up
  
  2. Update/View user profile
  
  3. Add money to wallet
  
  4. Pay money from one user’s wallet to another user’s wallet
  
  5. View passbook - All transactions
  
  
  ###Expectations
  
1. REST API endpoints with authentication
2. DAO
3. Object Oriented
4. DB Transaction
5. Logging
6. Exception Handling
7. Database - MySQL or anything else

###Steps to connect and running application

  $pre-requisite(mysql latest version,workbench)
  
  $git clone  https://github.com/shivlok/Fabhotels_Assignment.git
  
  $cd Fabhotels_Assignment
  
  $git checkout master
  
  $ in application.properties(src/main/resources packegae) EDIT THE FOLLOWING:
  
       #spring.datasource.url= jdbc:mysql://localhost:3306/{YOUR_DB_NAME}?useSSL=false

       #spring.datasource.username= {DB_USRNAME}

        #  spring.datasource.password= {DB_PASSWORD} //8080 default

        #server.port={PORT_OF_YOR_CHOICE

        #run all queries in src/main/resources/db/migration package

        #check the database is up and running

        #build the project as maven
  
  
  $API for user/register

  
    #curl --location --request POST 'http://localhost:8081/user/register' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "username":"Alok",
        "email":"ab@gmail.com",
        "password":"123456",
        "isActive":true
    }'
  
  $API for user/login
 


      curl --location --request POST 'http://localhost:8000/user/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "username":"Alok",
        "password":"123456"
    }'
     
    the response will give JWT token coppy it and then include it as Bearer token in any forthcoming request
    
    $API for creating a wallet
 


      curl --location --request POST 'http://localhost:8000/wallet/create' \
      --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJTaGl2bG9rRHViZXkiLCJpYXQiOjE2NjIxNTY3MTksImV4cCI6MTY2MjI0MzExOX0.iGnDI6Ndowj_3Yp4xxHFJsmaj4Ryg9inHweBaVSjlVK1f4l6rQC75gBNvUslXJTscD9s-gMDIRzUPNcoCSvq5A' \
      --header 'Content-Type: application/json' \
      --data-raw '{
    "walletBalance":50,
    "userId":"1",
    "currency":"inr"
}'
    
 $API for making a transaction from wallet
 
 
     curl --location --request POST 'http://localhost:8000/transaction/create/{walletId}' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJTaGl2bG9rRHViZXkiLCJpYXQiOjE2NjIxNTY3MTksImV4cCI6MTY2MjI0MzExOX0.iGnDI6Ndowj_3Yp4xxHFJsmaj4Ryg9inHweBaVSjlVK1f4l6rQC75gBNvUslXJTscD9s-gMDIRzUPNcoCSvq5A' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "transactionUniqueId":"11",
    "currency":"inr",
    "transactionType":"credit",
    "senderWalletId":"5",
    "amount":"10"
}'   

$API for showing all transaction for walletId

 
    curl --location --request GET 'http://localhost:8000/transaction/wallet/{walletId}' \
      --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdHVsIiwiaWF0IjoxNjYyMTgzNzc3LCJleHAiOjE2NjIyNzAxNzd9.8s-hwRyBCYcKQOJifl8ijiW3G42JOghWkadpW8RSBAXidLAeR34tbS0lcJteWi7Hy5yDkw1vURO7RO69gTuOFQ'


$view userdetails api

     curl --location --request GET 'http://localhost:8000/user/view/{userId}' \
      --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSYWh1bCIsImlhdCI6MTY2MjE3MzkxNywiZXhwIjoxNjYyMjYwMzE3fQ.q9zalrr2i8peKWzr2jab6AlW9Lpu1NCMBQgCfjDw7nkvjCy7X3YB4mC4wD8o4EtfcQ3bEdSN9D68J_reT2LPvg'

$update userdetails api

     curl --location --request PUT 'http://localhost:8000/user/update/{userId}' \
      --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSYWh1bCIsImlhdCI6MTY2MjE3MzkxNywiZXhwIjoxNjYyMjYwMzE3fQ.q9zalrr2i8peKWzr2jab6AlW9Lpu1NCMBQgCfjDw7nkvjCy7X3YB4mC4wD8o4EtfcQ3bEdSN9D68J_reT2LPvg'\
      --header 'Content-Type: application/json' \
    --data-raw '{
        "username":"Alok",
        "email":"ab@gmail.com",
        "password":"123456",
        "isActive":true
    }'   
    
$addMoney to wallet api

    curl --location --request PUT 'http://localhost:8000/transactio/5/addMoney' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGl2bG9rIiwiZXhwIjoxNjYzMTA2ODE2LCJqdGkiOiI1ZThlN2U3OC04MjUxLTQ3NWYtOTQzMC1hNzQyOTc3MTE0NDEifQ.xw0B8XCxDWjCDSvC4bWoKFYUl2qoGXJke_6skQfEzzI' \
    --header 'Content-Type: application/json' \
    --header 'Cookie: JSESSIONID=D30537A40F880A7059DFAC74F1D4DAD3' \
    --data-raw '{
        "amount":"100"
    }'
    
