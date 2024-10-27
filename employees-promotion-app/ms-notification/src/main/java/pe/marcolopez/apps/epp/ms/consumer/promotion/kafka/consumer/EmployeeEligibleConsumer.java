package pe.marcolopez.apps.epp.ms.consumer.promotion.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.kafka.event.EmployeeEligibleEvent;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeEligibleConsumer {

  @KafkaListener(
      topics = "${kafka.consumer.topic-eligible-employee}",
      groupId = "${kafka.consumer.group-id}"
  )
  public void consumeEmployeeEligible(ConsumerRecord<String, EmployeeEligibleEvent> record,
                                      Acknowledgment acknowledgment) {
    String key = record.key();
    EmployeeEligibleEvent employeeEligibleEvent = record.value();
    try {
      log.info("### Key: {}", key);
      processEmployeeEligibleEvent(employeeEligibleEvent);
      acknowledgment.acknowledge();
    } catch (Exception e) {
      log.error("Error with key: {}, details: {}", key, e.getMessage());
    }
  }

  private void processEmployeeEligibleEvent(EmployeeEligibleEvent event) {
    log.info("#################################################################################################");
    log.info("#################################################################################################");
    log.info("########################################## Start Email ##########################################");
    log.info("### To: {}", event.getEmail());
    log.info("### Subject: Congratulations, {} your have a Promotion!", event.getFirstname());
    log.info("### Body: Approve?: https://evalutemypromotion.pe/evaluation-promotion/{}", event.getId());
    log.info("########################################## Finish Email ##########################################");
    log.info("#################################################################################################");
    log.info("#################################################################################################");
    log.info("### Details of EmployeeEligibleEvent: {}", event);
    log.info("#################################################################################################");
    log.info("#################################################################################################");
    log.info("#################################################################################################");
  }
}
