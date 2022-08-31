FROM openjdk:11
LABEL maintainer=xuepeng@chinasofti.com


RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

COPY target/*.jar /app.jar
EXPOSE 9005

ENTRYPOINT ["/bin/sh","-c","java -jar app.jar"]