FROM openjdk:8-jdk-alpine
COPY build/libs/wol-1.war app.war
# ARG <name>[=<default value>]
ARG macaddrs=MyCom#4C:CC:6A:8E:DE:B2
ENV JAVA_OPTS=""
ENV MACADDRS=${macaddrs}
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.war","--config.macaddrs=${MACADDRS}"]