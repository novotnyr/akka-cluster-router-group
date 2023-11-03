package com.github.novotnyr.akka;

import akka.actor.typed.ActorSystem;
import com.github.novotnyr.akka.Worker.WorkData;

import java.util.UUID;

public class Runner {
    public static void main(String[] args) throws Exception {
        var system = ActorSystem.create(Worker.create(), "system");
        while (true) {
            system.tell(new WorkData(UUID.randomUUID().toString()));
            Thread.sleep(1000);
        }
    }
}
