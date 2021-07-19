package me.ukd.hazelcast.queue.sample;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import me.ukd.hazelcast.queue.sample.model.SampleModel;
import me.ukd.hazelcast.queue.sample.model.SampleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class SampleController {

    @Autowired
    SamplePublisher publisher;

    @Autowired
    SampleSubscriber subscriber;

    @Autowired
    HazelcastInstance instance;

    @RequestMapping("/add")
    public Long addModelToQueue() {
        SampleModel model = new SampleModel();
        model.setId(new Random().nextLong());
        publisher.publish(model);
        return model.getId();
    }

    @RequestMapping("/get")
    public Long getModelFromQueue() {
        SampleModel model = subscriber.subscribe();
        return model.getId();
    }

    @RequestMapping("/exec/{input}")
    public String execTask(@PathVariable String input) throws ExecutionException, InterruptedException {
        IExecutorService executorService = instance.getExecutorService( "executorService" );
        Future<String> future = executorService.submit( new SampleTask( input) );
        return future.get();
    }
}
