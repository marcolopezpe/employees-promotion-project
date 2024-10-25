package pe.marcolopez.apps.epp.ms.command.promotion.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.kafka.event.PromotionEmployeeEvent;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionEventProducer {

  @Value("${kafka.producer.topic-prom-emp-evaluate:topic-prom-emp-evaluate}")
  private String topicPromotionEmployeeEvaluate;
  private final KafkaTemplate<String, PromotionEmployeeEvent> kafkaTemplate;

  public void sendPromotionEmployeeEvaluate(PromotionEmployeeEvent event) {
    String key = UUID.randomUUID().toString();
    kafkaTemplate.send(topicPromotionEmployeeEvaluate, key, event);
    log.info("### Sent PromotionEventProducer>sendPromotionEmployeeEvaluate: {}", event);
  }
}
