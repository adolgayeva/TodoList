FROM openjdk:11 AS BUILD_IMAGE
ENV APP_HOME=/root/dev/app/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew -x :bootJar -x test build
COPY . .
RUN ./gradlew bootJar

FROM openjdk:11-jre-slim
WORKDIR /root/
COPY --from=BUILD_IMAGE /root/dev/app/build/libs/todo-list-backend-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java","-jar", "todo-list-backend-0.0.1-SNAPSHOT.jar"]