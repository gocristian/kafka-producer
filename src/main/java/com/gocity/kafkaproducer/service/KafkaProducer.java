package com.gocity.kafkaproducer.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NewTopic topic;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, NewTopic topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        sendBatchOfEvents();
    }

    public void sendEvent(String key, String value) {
        kafkaTemplate.send(topic.name(), key, value);
    }

    public void sendBatchOfEvents() {
        for(int i = 0; i < 100; i++) {
            sendEvent("k".concat(String.valueOf(i)), "v".concat(String.valueOf(i)));
        }
    }
}
