FROM openjdk:8-jdk-alpine
COPY build/libs/eureka_client-1.war app.war
# ARG <name>[=<default value>]
ARG macaddrs=MyCom#4C:CC:6A:8E:DE:B2
ENV JAVA_OPTS=""
EXPOSE ${PORT}
ENTRYPOINT ["java","-jar","/app.war","--config.macaddrs=${macaddrs}"]