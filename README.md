
`ConcourseapiApplicationTests` integrates with the Concourse ATC API (it pulls a specific build result and all the events associated with it) and needs to be run with properties set as described below

*IMPORTANT:* Please be aware of the difference between the build number (which is specific to a job) and the global build identifier. You will need the latter, which is only visible in the API.
To retrieve the identifier
* go to a build result in the GUI (e.g. {my-host}/teams/{my-team}/pipelines/{my-pipeline}/jobs/{my-job}/builds/{my-build-number})
* after the host, prefix the path with `api/v1/` (e.g. {my-host}/api/v1/teams/{my-team}/pipelines/{my-pipeline}/jobs/{my-job}/builds/{my-build-number})
* use the id field in the json response

## Running the poc via the integration test

* Add required Concourse url+username+password to your local environment (which will override those in application.properties). If using Eclipse, set Run Configuration with Environment containing Name: http.basic.auth.password Value: mysecret
* Change the Concourse team is needed in application.properties (default is main)
* Choose a build result and retrieve the "global" build identifier (not the build number) to pull the results and events from (update the variable int `globalBuildIdentifier = 52;` in `ConcourseapiApplicationTests.java`)
** To get the identifier prefix api/v1/
* Run test `/concourseapi/src/test/java/com/dellemc/concourseapi/ConcourseapiApplicationTests.java`
