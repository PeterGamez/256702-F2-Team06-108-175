@echo off
mvn clean install

if not exist build (
    mkdir build
)

move /Y target\server.jar build\server.jar
copy /Y .env.example build\.env.example
copy /Y application.yml.example build\application.yml.example