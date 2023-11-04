## Running

Run a single node:

    ./gradlew run -Pport=10001 -Phost=127.0.0.1 -PseedNode=127.0.0.1:10001

Run a secondary node:

    ./gradlew run -Pport=10002 -Phost=127.0.0.1 -PseedNode=127.0.0.1:10001
