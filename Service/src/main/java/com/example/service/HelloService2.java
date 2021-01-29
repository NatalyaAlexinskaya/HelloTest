package com.example.service;

import com.example.grpc.server.HelloResponse;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
public class HelloService2 {
    private KafkaProducer<String, String> kafkaProducer;

    @PostConstruct
    public void init() {
        String bootstrapServer = "kafka:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProducer = new KafkaProducer<String, String>(properties);
    }

    public HelloResponse redirectRequest(HelloResponse response) {
        String topic = "TestTopic";
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, response.getResponse());
        kafkaProducer.send(producerRecord);
        kafkaProducer.flush();
        kafkaProducer.close();
        return response;
    }
}
