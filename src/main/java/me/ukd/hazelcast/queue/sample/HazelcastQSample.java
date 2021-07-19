package me.ukd.hazelcast.queue.sample;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HazelcastQSample {

    public static void main(String[] s) {
        SpringApplication.run(HazelcastQSample.class, s);
    }

    @Bean
    public Config config() {
        Config config = new Config();
        config.getNetworkConfig().getJoin().getMulticastConfig().setMulticastGroup("224.0.0.1").setEnabled(true);
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(config());
    }
}
