FROM tomcat:8.5.30-jre8

RUN mkdir /build/
WORKDIR /build/

COPY . .

RUN apt-get update \
    && apt-get install -y \
        openjdk-8-jdk
RUN ./gradlew war

RUN rm -rf /usr/local/tomcat/webapps/*
RUN cp /build/target/ROOT.war /usr/local/tomcat/webapps/.
