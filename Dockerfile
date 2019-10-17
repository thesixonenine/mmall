FROM openjdk:8-jdk-alpine
#RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/mmall-service
ADD ./target/ROOT.jar /usr/local/mmall-service/
#ADD run.sh run.sh
#RUN chmod +x run.sh
#CMD ./run.sh
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/local/mmall-service/ROOT.jar"]