mvn clean package

if [ ! -d "build" ]; then
  mkdir build
fi

mv target/server-1.0.jar build/server.jar

java -jar build/server.jar