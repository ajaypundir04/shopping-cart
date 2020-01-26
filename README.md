# Shopping Cart

### Assumptions
1. Customer Type is unique.
2. We will keep the discount slabs on the basis of customer type.
3. purchased amount and customer will be provided each time to calculate the billAmount.
4. `com.cdk.global.shopping.cart.model.Customer` is used to handle customer type 
5. Input Format slab  `premium 0 4000 10`
6. Input Format invoice `premium 1000`         
         
    
### Test
1. We can run `./gradlew test`  for running test cases

### Setup Dependencies
1. Java : JDK1.8
2. Build Tool : Gradle 4.10.3
3. Junit : 4.12
4. Mockito : 2.7.22
5. Hamcrest: 1.3


### Start Application
1. ApplicationLaucher is the main entry for application.
2. we can execute the command `art <slab_details_file_path> <invoice_details_file_path>` to run the application.
3. `bin/setup` for build and test the application.