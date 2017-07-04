FROM java:8
EXPOSE 8080
COPY ./database.json .
ADD /target/SpringDocker.jar SpringDocker.jar
ENTRYPOINT ["java", "-jar", "SpringDocker.jar"]