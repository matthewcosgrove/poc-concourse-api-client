
NOTE: `ConcourseapiApplicationTests` integrates with the API (it pulls a specific build result and all the events associated with it) and needs to be run with properties set as described below

* Add required Concourse url+username+password to your local environment (which will override those in application.properties). If using Eclipse, set Run Configuration with Environment containing Name: http.basic.auth.password Value: mysecret
* Change the Concourse team is needed in application.properties (default is main)
* Choose a build number to pull the results and events from (update the variable int `globalBuildIdentifier = 52;` in `ConcourseapiApplicationTests.java`)
* Run test `/concourseapi/src/test/java/com/dellemc/concourseapi/ConcourseapiApplicationTests.java`
