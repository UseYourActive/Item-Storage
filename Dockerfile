FROM openjdk:17
WORKDIR /Item-Storage
COPY rest/target/tinqin-storage.jar zoo-storage.jar
EXPOSE 8082
CMD ["java", "-jar", "zoo-storage.jar"]