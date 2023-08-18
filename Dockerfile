FROM openjdk:17

RUN mkdir /item-storage

WORKDIR /item-storage

COPY rest/target/tinqin-storage.jar item-storage.jar

EXPOSE 8082

CMD ["java", "-jar", "item-storage.jar"]