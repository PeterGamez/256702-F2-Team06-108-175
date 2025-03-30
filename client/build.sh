mvn clean install

if [ ! -d "build" ]; then
  mkdir build
fi

if [ -f "build/client.jar" ]; then
  rm build/client.jar
fi

mv target/client-jar-with-dependencies.jar build/client.jar