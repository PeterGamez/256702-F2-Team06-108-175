mvn clean install

if [ ! -d "build" ]; then
  mkdir build
fi

if [ -f "build/server.jar" ]; then
  rm build/server.jar
fi

mv target/server.jar build/server.jar