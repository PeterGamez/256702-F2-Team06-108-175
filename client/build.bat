@echo off
mvn clean install

if not exist build (
    mkdir build
)

move /Y target\client-jar-with-dependencies.jar build\client.jar