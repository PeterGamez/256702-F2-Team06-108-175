mvn clean package

if [ ! -d "build" ]; then
  mkdir build
fi

mv target/client-jar-with-dependencies.jar build/client.jar