mvn clean package

if [ ! -d "build" ]; then
  mkdir build
fi

mv target/server.jar build/server.jar

java -jar build/server.jar