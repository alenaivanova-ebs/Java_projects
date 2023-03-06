FROM openjdk:17-jdk-slim
ADD build/libs/Runner*.jar app.jar
EXPOSE 8080
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jadocker rm -f mysqldbr"]