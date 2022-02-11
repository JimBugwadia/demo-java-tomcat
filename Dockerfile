FROM tomcat:9.0.58-jre8-temurin-focal
LABEL author "jim@nirmata.com"

ADD build/libs/service.war /usr/local/tomcat/webapps/service.war

ENTRYPOINT ["catalina.sh", "run"]
