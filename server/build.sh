mvn clean install

if [ ! -d "build" ]; then
  mkdir build
fi

mv target/server.jar build/server.jar
cp .env.example build/.env.example