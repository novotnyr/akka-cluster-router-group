## Running

Run a single node:

    ./gradlew run -P=akkaHost=127.0.0.1 -PakkaPort=10001 -PakkaSeedNode=127.0.0.1:10001 -PakkaRole=guardian

Run a worker node:

    ./gradlew run -P=akkaHost=127.0.0.1 -PakkaPort=10002 -PakkaSeedNode=127.0.0.1:10001 -PakkaRole=worker

Run a secondary worker node:

    ./gradlew run -P=akkaHost=127.0.0.1 -PakkaPort=10003 -PakkaSeedNode=127.0.0.1:10001 -PakkaRole=worker
