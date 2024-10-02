FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/Backend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java","-jar","/backend.jar"]