package com.github.novotnyr.akka;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import static com.github.novotnyr.akka.Worker.WorkData;

public class Worker extends AbstractBehavior<WorkData> {
    public static Behavior<WorkData> create() {
        return Behaviors.setup(Worker::new);
    }

    private Worker(ActorContext<WorkData> context) {
        super(context);
    }

    @Override
    public Receive<WorkData> createReceive() {
        return newReceiveBuilder()
                .onMessage(WorkData.class, this::onWorkData)
                .build();
    }

    private Behavior<WorkData> onWorkData(WorkData workData) {
        getContext().getLog().debug("Processing '{}'", workData.data());
        return Behaviors.same();
    }

    public record WorkData(String data) {}
}

