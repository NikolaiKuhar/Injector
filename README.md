TestTask:
Using the Java Reflection API and classes from the java.lang.reflect package, implement the simplest version of the Dependency Injection container.
The result of the work should be a Maven (or Gradle) project that can be built into one or more JAR libraries.
Third party libraries are not allowed. 
All code must be written by you. Except for libraries for testing.
Unit-tests should be written to check the library operation.

My imlepentation ArrayList.

Build

mvn clean install

RUN

cd target

java -jar Injector-1.0-SNAPSHOT.jar
