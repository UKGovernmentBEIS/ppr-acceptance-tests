# DEPRECATED - these tests have been incorporated into https://github.com/UKGovernmentBEIS/payment-practices-reporting as integration tests and are no longer maintained

# Acceptance tests for Payment Practices Reporting service

The ppr project is here https://github.com/UKGovernmentBEIS/payment-practices-reporting

# Running the tests

* Ensure you have the PhantomJS driver installed on your local machine
* Checkout and run the ppr service locally (see the README for the service for more details).
* Run the tests from the base directory of this project using `sbt test`
    
This will run all the test cases against the application running on `localhost:9000` using the `phantomjs` driver in headless mode. 
You can use command-line parameters to change things. E.g.
 
    sbt -Dbrowser=firefox test
    
to run the tests using Firefox. You can also use `browser=chrome`.

The tests can be run against different environments using `-Denvironment=<env>` where environment can be one of:

* dev (the default, runs against localhost:9000)
* live (runs against https://payment-practices-reporting.herokuapp.com)

