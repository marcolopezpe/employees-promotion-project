package pe.marcolopez.apps.epp.ms.notification.config;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import pe.marcolopez.apps.epp.ms.kafka.event.EmployeeEligibleEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

  @Value("${spring.kafka.consumer.bootstrap-servers:localhost:19092}")
  private String kafkaServers;

  @Value("${spring.kafka.consumer.properties.schema.registry.url:localhost:8081}")
  private String schemaRegistryUrl;

  @Bean
  public ConsumerFactory<String, EmployeeEligibleEvent> consumerFactory() {
    Map<String, Object> kafkaProperties = new HashMap<>();
    kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
    kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
    kafkaProperties.put("schema.registry.url", schemaRegistryUrl);
    return new DefaultKafkaConsumerFactory<>(kafkaProperties);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, EmployeeEligibleEvent> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, EmployeeEligibleEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
