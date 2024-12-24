FROM openjdk:17-jdk-slim
EXPOSE 8080
#ADD target/demo-0.0.1-SNAPSHOT.jar devops-tekup.jar
#ENTRYPOINT ["java","-jar","/devops-tekup.jar"]

ADD target/demo-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]



