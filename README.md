## Running

## Running from IDE

### A worker node

Set the following Java Virtual Machine properties which are handled by akka

- *instance port in cluster*: 

    `-Dakka.remote.artery.canonical.port=10001`

    By default, 25520 port number is used.
 
- *instance hostname*: 

    `-Dakka.remote.artery.canonical.hostname=127.0.0.1`. 

    Otherwise, Akka Cluster will autodetect an IP address.

- reference to any running cluster node -- _seed node_. 

    By default, we will refer to the primary cluster node represented by the first worker node.
Note that even a single-node cluster requires a seed node. 

    `-Dakka.cluster.seed-nodes.0=akka://system@127.0.0.1:10001`.

### Second and third worker node

Second and third worker node require a specific ports, such as `10002`, `10003`.
They will refer to the same seed node.

## Running via Gradle

Run a single node:

    ./gradlew run -P=akkaHost=127.0.0.1 -PakkaPort=10001 -PakkaSeedNode=127.0.0.1:10001 -PakkaRole=guardian

Run a worker node:

    ./gradlew run -P=akkaHost=127.0.0.1 -PakkaPort=10002 -PakkaSeedNode=127.0.0.1:10001 -PakkaRole=worker

Run a secondary worker node:

    ./gradlew run -P=akkaHost=127.0.0.1 -PakkaPort=10003 -PakkaSeedNode=127.0.0.1:10001 -PakkaRole=worker


# Architecture

The `Runner` runs in one of two roles.

- `worker` mode: creates a new actor system with a single `Worker` actor.
This will launch a single actor per application instance. 
Each `Worker` registers itself in the global Akka Receptionist under a specific service key.
- `guardian` mode: creates a new actor system containing a single group router.
Such a router will autodetect all Worker actors with the specific service key.
All messages sent to the actor system will be routed to all Worker nodes, essentially creating a distributed system.

# Future Work 

- We can distinguish between two modes in a specific actor, which will discover the cluster [node role](https://doc.akka.io/docs/akka/current/typed/cluster.html#node-roles) and spawn a specific set of actors.
- We can create a single-node worker pool, where a _pool router_ will handle incoming messages and route to the `Worker` actors.
Then, we can create both concurrent and distributed system with a common underlying philosphy.
