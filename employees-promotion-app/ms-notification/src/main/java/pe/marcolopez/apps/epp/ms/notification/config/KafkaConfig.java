package pe.marcolopez.apps.epp.ms.notification.config;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import pe.marcolopez.apps.epp.ms.kafka.event.EmployeeEligibleEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

  @Value("${kafka.bootstrap-servers:localhost:19092}")
  private String kafkaServers;

  @Value("${kafka.schema-registry-url:localhost:8081}")
  private String schemaRegistryUrl;

  @Value("${kafka.consumer.auto-offset-reset:latest}")
  private String autoOffsetReset;

  @Value("${kafka.consumer.enable-auto-commit:true}")
  private boolean enableAutoCommit;

  @Bean
  public ConsumerFactory<String, EmployeeEligibleEvent> consumerFactory() {
    Map<String, Object> kafkaProperties = new HashMap<>();
    kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
    kafkaProperties.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
    kafkaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
    kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
    kafkaProperties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
    kafkaProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
    return new DefaultKafkaConsumerFactory<>(kafkaProperties);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, EmployeeEligibleEvent> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, EmployeeEligibleEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
    return factory;
  }
}
