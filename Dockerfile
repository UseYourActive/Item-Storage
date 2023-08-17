FROM openjdk:17
WORKDIR /Item-Storage
COPY rest/target/tinqin-storage.jar item-storage.jar
EXPOSE 8082
CMD ["java", "-jar", "item-storage.jar"]