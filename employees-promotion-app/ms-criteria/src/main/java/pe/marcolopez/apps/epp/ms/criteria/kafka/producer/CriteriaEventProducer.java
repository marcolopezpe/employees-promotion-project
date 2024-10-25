package pe.marcolopez.apps.epp.ms.criteria.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.kafka.event.EmployeeEligibleEvent;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CriteriaEventProducer {

  @Value("${kafka.producer.topic:topic-eligible-employee}")
  private String topicName;
  private final KafkaTemplate<String, EmployeeEligibleEvent> kafkaTemplate;

  public void sendEvent(EmployeeEligibleEvent event) {
    String key = UUID.randomUUID().toString();
    kafkaTemplate.send(topicName, key, event);
    log.info("### CriteriaEventProducer>sendEvent: {}", event);
  }
}
