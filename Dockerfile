FROM openjdk:8-jdk-alpine
COPY build/libs/wol-1.war app.war
# ARG <name>[=<default value>]
ARG macaddrs=MyCom#4C:CC:6A:8E:DE:B2
ARG port=8080
ENV JAVA_OPTS=""
ENV MACADDRS=${macaddrs}
ENV PORT=${port}
EXPOSE ${PORT}
ENTRYPOINT ["java","-jar","/app.war","--config.macaddrs=${MACADDRS}","--server.port=${PORT}"]