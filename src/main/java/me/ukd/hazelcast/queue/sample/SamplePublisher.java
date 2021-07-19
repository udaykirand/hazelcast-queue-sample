package me.ukd.hazelcast.queue.sample;

import me.ukd.hazelcast.queue.sample.model.SampleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SamplePublisher {

    @Autowired
    QueueClient queueClient;

    public void publish(SampleModel model) {
        System.out.println("adding model: " + model.getId());
        queueClient.add(model);
        System.out.println("model added: " + model.getId());
    }
}
