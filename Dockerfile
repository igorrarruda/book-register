FROM maven:3.3-jdk-8 as builder

WORKDIR /usr/src/mymaven
COPY . .

RUN mvn clean install -f . && mkdir /usr/src/wars/
RUN find . -iname '*.war' -exec cp {} /usr/src/wars/ \;

FROM tomcat:7.0.90-jre8
COPY --from=builder /usr/src/wars/* /usr/local/tomcat/webapps/
COPY tomcat-users.xml $CATALINA_HOME/conf/