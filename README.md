# demo-java-tomcat

A demo application written in Java that runs on Tomcat

## Building 

````bash
gradle docker 
gradle dockerPush
````

## Running

````bash
docker run -p 8080:8080 -it nirmata/demo-java-tomcat:v1
````

Customize the image color and service name using the following environment variables:
  * SERVICE_NAME
  * SERVICE_COLOR

for example:

````bash
docker run -p 8080:8080 -it -e SERVICE_NAME="foo" -e SERVICE_COLOR="blue" nirmata/demo-java-tomcat:v1
````

````bash
docker run -p 8080:8080 -it -e SERVICE_NAME="foo" -e SERVICE_COLOR="green" nirmata/demo-java-tomcat:v1
````

## Endpoints

| App          |  Address                       |
|--------------|--------------------------------|
| Tomcat       | http://\<host>:\<port>         |
| Java service | http://<host>:<port>/service/  |