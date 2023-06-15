FROM tomcat:9.0.76-jdk17-corretto
LABEL author "jim@nirmata.com"

ADD ./build/libs/service.war /usr/local/tomcat/webapps/service.war

ENTRYPOINT ["catalina.sh", "run"]
