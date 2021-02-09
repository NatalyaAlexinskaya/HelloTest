package com.example.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class HelloService3 {
 private KafkaConsumer<String, String> kafkaConsumer;

    @PostConstruct
            public void init() {
        String bootstrapServer = "kafka:9092";
        String groupId = "exampleGroup";
        String topic = "TestTopic";

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));

        while (true) {
            ConsumerRecords<String, String> consumerRecord = kafkaConsumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record: consumerRecord) {
                System.out.println("Response: " + record.value());
            }
        }
    }
}
