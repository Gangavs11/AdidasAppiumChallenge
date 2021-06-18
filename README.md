# AdidasAppiumChallenge

This is a Appium mobile automation sample project implemented with TestNG framework, Maven.

Running the Tests

Pre-requisites:
* JDK 8
* Maven is installed in the machine and configured.
* Android studio is installed for the emulator. Create a virtual device and do the necessary settings.
* Update the config.properties file according to the virtual device.
* Launch the avd in the emulator before running tests.
* environment variables must be set for JAVA_HOME, NODE_HOME, ANDROID_HOME
* In order to run the Appium server automatically, update the NodePath, AppiumMainJS_Path in AppiumServer class based on the location where node, appium are installed. file present under src\main\java\com\qa\appiumServer.

Running tests using Maven

mvn test -DsuiteXmlFile=testng.xml

We can also run tests through IDE using testng.xml. Detailed extent reports are generated.
  







