FROM java:11
ADD target/*.jar administrator-server.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/administrator-server.jar"]