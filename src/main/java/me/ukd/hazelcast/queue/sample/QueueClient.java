package me.ukd.hazelcast.queue.sample;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import me.ukd.hazelcast.queue.sample.model.SampleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class QueueClient {

    @Autowired
    HazelcastInstance hazelcastInstance;

    List<Object> drain() {
        List<Object> retList = new ArrayList<>();
        hazelcastInstance.getQueue("sample-queue").drainTo(retList );
        return retList;
    }

    Object poll() {
        return hazelcastInstance.getQueue("sample-queue").poll();
    }

    void add(SampleModel model) {
        hazelcastInstance.getQueue("sample-queue").add(model);
    }
}
