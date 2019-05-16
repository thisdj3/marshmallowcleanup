# marshmallowcleanup
A web-service which takes an input of co-ordinates of area dimensions, the locations of the oil patches, the initial location of the cleaner and a string of navigation instructions (NESW) as input and then outputs the following:  The final cleaner position (X, Y) &amp; The number of patches of oil the robot cleaned up.

I used the Spring Tool suite with Eclipse IDE to create and test the code. To build:

-Using Eclipse IDE build as > Maven project and set the goal as the package name "cleaner"

-A jar file will be created which will run the application

-Input JSON string into a HTTP POST message using localhost:8080?querystring

The java-Json.jar reference library was used.
