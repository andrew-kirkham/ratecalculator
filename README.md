![](https://travis-ci.org/andrew-kirkham/ratecalculator.svg?branch=master)

# RateCalculator

## What is it
This project calculates parking rates for a given time interval.


## Endpoints
This application has two roots: `docs` and `api`
* `<base_url>/docs` is the Swagger documentation for `<base_url>/api`
* `<base_url>/api` is the endpoint for all actions 
    * `<base_url>/api/metrics` provides statistics and healthchecks about the application

## Documentation
Full documentation can be found by visiting `<base_url>/docs` and viewing the swagger documentation.
This documentation outlines the expected request response types.
Both JSON and XML are supported.

## Building

### Local builds
Locally this project can be built using gradle. 

* `gradle run` will run the application on localhost
* `gradle war` will build the war file used for deployment
* `gradle test` will execute the tests

### CI Builds
This project is integrated with Travis CI for builds. 
Travis will automagically run the tests on each commit and display the status at the top of this readme.

## Deployment
### AWS CodeDeploy
Provided are necessary scripts for an AWS CodeDeploy deployment.

### Spinnaker/Foremast
Provided in the runway folder are configuration files for a Spinnaker deployment. 
If Foremast is used, this files can be loaded to automate the spinnaker pipeline directly.