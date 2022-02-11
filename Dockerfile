FROM tomcat:9-jre8
LABEL author "jim@nirmata.com"

ADD service.war /usr/local/tomcat/webapps/service.war

ENTRYPOINT ["catalina.sh", "run"]
