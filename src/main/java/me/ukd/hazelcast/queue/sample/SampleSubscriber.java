package me.ukd.hazelcast.queue.sample;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import me.ukd.hazelcast.queue.sample.model.SampleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleSubscriber {

    @Autowired
    HazelcastInstance instance;

    @Scheduled(fixedRate = 5000L)
    public SampleModel subscribe() {
        System.out.println("Polling for model");
        SampleModel polledModel = null;
        IQueue queue = instance.getQueue("sample-queue");
        if(!queue.isEmpty()) {
            polledModel = (SampleModel) queue.poll();
            System.out.println("Polled for model: " + (polledModel == null ? "=====NULL=====" : polledModel.getId()));
        }
        return polledModel;
    }

}
