package me.ukd.hazelcast.queue.sample.model;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class SampleTask implements Callable<String>, Serializable, HazelcastInstanceAware {

    String input = null;

    private transient HazelcastInstance hazelcastInstance;

    public SampleTask() {
    }

    public void setHazelcastInstance( HazelcastInstance hazelcastInstance ) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public SampleTask(String input) {
        this.input = input;
    }

    public String call() {
        System.out.println("Executing here " + input);
        return hazelcastInstance.getCluster().getLocalMember().toString() + ":" + input;
    }
}
