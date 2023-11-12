package com.fastcampuspay.money.adapter.in.kafka;

import com.fastcampuspay.common.CountDownLatchManager;
import com.fastcampuspay.common.LoggingProducer;
import com.fastcampuspay.common.RechargingMoneyTask;
import com.fastcampuspay.common.SubTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

//@Component
public class RechargingMoneyResultConsumer {

    private final KafkaConsumer<String, String> consumer;

    private final LoggingProducer loggingProducer;

    @NotNull
    private final CountDownLatchManager countDownLatchManager;

    public RechargingMoneyResultConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                        @Value("${task.result.topic}") String topic,
                        LoggingProducer loggingProducer,
                        CountDownLatchManager countDownLatchManager) {

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "my-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<String, String>(props);
        this.loggingProducer = loggingProducer;
        this.countDownLatchManager = countDownLatchManager;
        consumer.subscribe(Collections.singletonList(topic));

        Thread consumerThread = new Thread(() -> {

            try{

                ObjectMapper mapper = new ObjectMapper();
                while(true){
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

                    for (ConsumerRecord<String, String> record : records) {

                        System.out.println("Received message: " + record.key() + " / " + record.value());


                        RechargingMoneyTask task;
                        try {
                            task = mapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        //task result
                        boolean taskResult = true;
                        for (SubTask subTask : task.getSubTaskList()) {

                            if(subTask.getStatus().equals("fail")){
                                taskResult = false;
                                break;
                            }
                        }


                        if(taskResult){
                            this.loggingProducer.sendMessage(task.getTaskID(), "task success");
                            this.countDownLatchManager.setDataForKey(task.getTaskID(), "success");

                        }else{
                            this.loggingProducer.sendMessage(task.getTaskID(), "task failed");
                            this.countDownLatchManager.setDataForKey(task.getTaskID(), "failed");
                        }

                        // record : RechargingMoneyTask ( all subtask is done)
                     /*   try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/

                        CountDownLatch countDownLatch = countDownLatchManager.getCountDownLatch(task.getTaskID());

                        countDownLatch.countDown();
                    }
                }
            }finally {
                consumer.close();
            }
        });

        consumerThread.start();
    }
}
