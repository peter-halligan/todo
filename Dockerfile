FROM 11-jre-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]