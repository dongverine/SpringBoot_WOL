# WOL Server

SpringBoot2.4.5 + Gradle6.8







##도커파일내용 [Dockerfile]
```
FROM openjdk:8-jdk-alpine
COPY build/libs/wol-1.war app.war
# ARG <name>[=<default value>]
ARG macaddrs=MyCom#4C:CC:6A:8E:DE:B2
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.war","--config.macaddrs=${macaddrs}"]
```

##도커이미지빌드
```
docker build --tag dongverine/wol_server .
```

##도커이미지저장
```
docker save -o wol_server.tar dongverine/wol_server
```

##도커이미지 실행
```
##docker run -p HOST_PORT:DOCKER_PORT -e 파라미터명=파라미터 --network="브릿지" --name 도커컨테이너명 이미지[:버젼]
docker run -p 8080:8080 -e "macaddrs=MyCom#4C:CC:6A:8E:DE:B2" --network="bridge" --name WOL_Server wol_server
```
