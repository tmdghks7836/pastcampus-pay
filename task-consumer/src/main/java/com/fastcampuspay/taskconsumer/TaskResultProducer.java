//package com.fastcampuspay.taskconsumer;
//
//import com.fastcampuspay.common.RechargingMoneyTask;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Properties;
//
//@Component
//public class TaskResultProducer {
//
//    private final KafkaProducer<String, String> producer;
//
//    private final String topic;
//
//    public TaskResultProducer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServer,
//                              @Value("${task.result.topic}") String topic) {
//
//        Properties props = new Properties();
//        props.put("bootstrap.servers", bootstrapServer);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        producer = new KafkaProducer<String, String>(props);
//        this.topic = topic;
//    }
//
//    public void sendTaskResult(String key, Object value){
//
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonStringToProduce;
//        // json String
//
//        try{
//            jsonStringToProduce = mapper.writeValueAsString(value);
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//
//        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonStringToProduce);
//        producer.send(record, (metadata, exception) -> {
//
//            if(exception == null){
//
//            }else{
//                exception.printStackTrace();
//            }
//
//        });
//    }
//
//
//
//}
