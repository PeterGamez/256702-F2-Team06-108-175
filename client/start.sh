mvn clean package

if [ ! -d "build" ]; then
  mkdir build
fi

mv target/client-1.0-jar-with-dependencies.jar build/client.jar

java -jar build/client.jar