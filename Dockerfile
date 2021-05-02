FROM openjdk:8-jdk-alpine
COPY build/libs/wol-1.war app.war
# ARG <name>[=<default value>]
ARG macaddrs=MyCom#4C:CC:6A:8E:DE:B2
ARG loginid
ARG loginpass
ARG port=8080
ENV JAVA_OPTS=""
ENV MACADDRS=${macaddrs}
ENV PORT=${port}
ENV LOGINID=${loginid}
ENV LOGINPASS=${loginpass}
EXPOSE ${PORT}
ENTRYPOINT ["java","-jar","/app.war","--config.macaddrs=${MACADDRS}","--config.id='${LOGINID}'","--config.pass='${LOGINPASS}'","--server.port=${PORT}"]