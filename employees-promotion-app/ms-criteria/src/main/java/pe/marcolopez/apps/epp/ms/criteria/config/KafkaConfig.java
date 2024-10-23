package pe.marcolopez.apps.epp.ms.criteria.config;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import pe.marcolopez.apps.epp.ms.kafka.event.EmployeeEligibleEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

  @Value("${spring.kafka.bootstrap-servers:localhost:19092}")
  private String kafkaServers;

  @Value("${spring.kafka.properties.schema.registry.url:localhost:8081}")
  private String schemaRegistryUrl;

  @Bean
  public ProducerFactory<String, EmployeeEligibleEvent> producerFactory() {
    Map<String, Object> kafkaProperties = new HashMap<>();
    kafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
    kafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    kafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
    kafkaProperties.put("schema.registry.url", schemaRegistryUrl);
    return new DefaultKafkaProducerFactory<>(kafkaProperties);
  }

  @Bean
  public KafkaTemplate<String, EmployeeEligibleEvent> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
