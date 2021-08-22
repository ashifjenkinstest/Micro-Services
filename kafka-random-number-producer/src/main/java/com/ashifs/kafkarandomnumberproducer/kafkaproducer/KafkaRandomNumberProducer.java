package com.ashifs.kafkarandomnumberproducer.kafkaproducer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class KafkaRandomNumberProducer {


    private static final int MIN= 10;
    private static final int MAX = 100_000;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    
    /*

    RandomNumberProducer –
    We have autowired the KafkaTemplate. We have already setup all the cluster & topic information via application.yml
    The produce method is scheduled to execute every second. Whenever it is executed, it writes a message into the Kafka topic.
    I have explicitly added the Sysout statement – Just to see which host produced which number just in case of multiple instances of the producers are running. We will understand the use in the next article

    */
    
    @Scheduled(fixedRate = 1000)
    private void producer() throws UnknownHostException{

        int random = ThreadLocalRandom.current().nextInt(MIN,MAX);
        this.kafkaTemplate.sendDefault(String.valueOf(random).concat("_PRD"));

        String hostName = InetAddress.getLocalHost().getHostName();
        System.out.println(String.format("%s produced %s", hostName, String.valueOf(random).concat("_PRD")));
    }
    
}
