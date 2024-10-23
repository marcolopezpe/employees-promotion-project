package pe.marcolopez.apps.epp.ms.notification.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeEligibleConsumer {

  @KafkaListener(
      topics = "${spring.kafka.topic}",
      groupId = "${spring.kafka.consumer.group-id}"
  )
  public void consumeEmployeeEligible(ConsumerRecord<String, EmployeeEligibleConsumer> record,
                                      Acknowledgment acknowledgment) {
    String key = record.key();
    EmployeeEligibleConsumer employeeEligibleConsumer = record.value();

    log.info("### Key: {}", key);
    log.info("### EmployeeEligibleConsumer: {}", employeeEligibleConsumer);

    acknowledgment.acknowledge();
  }
}
