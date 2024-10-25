package pe.marcolopez.apps.epp.ms.command.promotion.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.command.promotion.entity.PromotionEntity;
import pe.marcolopez.apps.epp.ms.command.promotion.repository.PromotionRepository;
import pe.marcolopez.apps.epp.ms.command.promotion.util.ConvertUtil;
import pe.marcolopez.apps.epp.ms.command.promotion.util.PromotionUtil;
import pe.marcolopez.apps.epp.ms.kafka.event.EmployeeEligibleEvent;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeEligibleConsumer {

  private final PromotionRepository promotionRepository;

  @KafkaListener(
      topics = "${kafka.consumer.topic-eligible-employee}",
      groupId = "${kafka.consumer.group-id}"
  )
  public void consumeEmployeeEligible(ConsumerRecord<String, EmployeeEligibleEvent> record,
                                      Acknowledgment acknowledgment) {
    String key = record.key();
    long timestamp = record.timestamp();
    EmployeeEligibleEvent employeeEligibleEvent = record.value();
    try {
      log.info("### Key: {}", key);
      processEmployeeEligibleEvent(employeeEligibleEvent, timestamp);
      acknowledgment.acknowledge();
    } catch (Exception e) {
      log.error("Error with key: {}, details: {}", key, e.getMessage());
    }
  }

  private void processEmployeeEligibleEvent(EmployeeEligibleEvent event, long timestamp) {
    promotionRepository.save(
        PromotionEntity
            .builder()
            .employeeId(UUID.fromString(event.getId()))
            .proposedLevel(ConvertUtil.proposedLevel(event.getCurrentLevel()))
            .status(PromotionUtil.PROMOTION)
            .requestDate(ConvertUtil.convertToLocalDate(timestamp))
            .leaderId(UUID.fromString(event.getLeaderId()))
            .period(event.getPeriodLevel())
            .createdAt(LocalDateTime.now())
            .build()
    );
  }
}
