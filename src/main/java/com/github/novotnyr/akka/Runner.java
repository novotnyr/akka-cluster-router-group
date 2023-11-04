package com.github.novotnyr.akka;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.Routers;

import java.util.UUID;

import static com.github.novotnyr.akka.Worker.WORK_DATA_SERVICE_KEY;

public class Runner {
    public static void main(String[] args) throws Exception {
        String clusterRole = System.getProperty("akka.cluster.roles.0");

        switch (clusterRole) {
            case "worker" -> runWorker();
            case "guardian" -> runGuardian();
        }
    }

    private static void runGuardian() {
        var router = Routers.group(WORK_DATA_SERVICE_KEY);
        var system = ActorSystem.create(router, "system");
        while (true) {
            try {
                system.tell(new Worker.WorkData(UUID.randomUUID().toString()));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private static void runWorker() {
        ActorSystem.create(Worker.create(), "system");
    }
}
