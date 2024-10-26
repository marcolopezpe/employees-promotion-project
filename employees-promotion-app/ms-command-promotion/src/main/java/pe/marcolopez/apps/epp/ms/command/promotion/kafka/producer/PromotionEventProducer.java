package pe.marcolopez.apps.epp.ms.command.promotion.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.kafka.event.PromotionEmployeeEvent;
import pe.marcolopez.apps.epp.ms.kafka.event.PromotionLeaderEvent;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionEventProducer {

  @Value("${kafka.producer.topic-prom-emp-evaluate:topic-prom-emp-evaluate}")
  private String topicPromotionEmployeeEvaluate;
  @Value("${kafka.producer.topic-prom-leader-evaluate:topic-prom-leader-evaluate}")
  private String topicPromotionLeaderEvaluate;
  private final KafkaTemplate<String, PromotionEmployeeEvent> kafkaTemplateEmployee;
  private final KafkaTemplate<String, PromotionLeaderEvent> kafkaTemplateLeader;

  public void sendPromotionEmployeeEvaluate(PromotionEmployeeEvent event) {
    String key = UUID.randomUUID().toString();
    kafkaTemplateEmployee.send(topicPromotionEmployeeEvaluate, key, event);
    log.info("### Sent PromotionEventProducer>sendPromotionEmployeeEvaluate: {}", event);
  }

  public void sendPromotionLeaderEvaluate(PromotionLeaderEvent event) {
    String key = UUID.randomUUID().toString();
    kafkaTemplateLeader.send(topicPromotionLeaderEvaluate, key, event);
    log.info("### Sent PromotionEventProducer>sendPromotionLeaderEvaluate: {}", event);
  }
}
