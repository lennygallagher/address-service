package ch.adesso.address.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class KafkaProvider {

    public static final String TOPIC = "toDefine";
    public static final String KAFKA_ADDRESS = "localhost:29092,localhost:39092,localhost:49092";
    public static final String GROUP_ID = "toDefine";

    private KafkaProducer<String, String> producer;
    private KafkaConsumer<String, String> consumer;

    @PostConstruct
    public void init() {
        this.producer = createProducer();
        this.consumer = createConsumer();
    }

    @Produces
    public KafkaProducer<String, String> getProducer() {
        return producer;
    }

    @Produces
    public KafkaConsumer<String, String> getConsumer() {
        return consumer;
    }

    public KafkaProducer<String, String> createProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KAFKA_ADDRESS);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<>(properties);
    }

    public KafkaConsumer<String, String> createConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KAFKA_ADDRESS);
        properties.put("group.id", GROUP_ID + UUID.randomUUID().toString());
        properties.put("auto.offset.reset", "earliest");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(TOPIC));
        return consumer;
    }
}
