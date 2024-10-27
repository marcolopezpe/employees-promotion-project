package pe.marcolopez.apps.epp.ms.consumer.promotion.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.consumer.promotion.document.PromotionDocument;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.consumer.promotion.mapper.PromotionMapper;
import pe.marcolopez.apps.epp.ms.consumer.promotion.repository.PromotionRepository;
import pe.marcolopez.apps.epp.ms.consumer.promotion.webclient.EmployeeWebClientService;
import pe.marcolopez.apps.epp.ms.kafka.event.PromotionEmployeeEvent;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionEmployeeConsumer {

  private final EmployeeWebClientService employeeWebClientService;
  private final PromotionRepository promotionRepository;
  private final PromotionMapper promotionMapper;

  @KafkaListener(
      topics = "${kafka.consumer.topic-prom-emp-evaluate}",
      groupId = "${kafka.consumer.group-id}"
  )
  public void consumePromotionByEmployee(ConsumerRecord<String, PromotionEmployeeEvent> record,
                                         Acknowledgment ack) {
    log.info("### Consuming Promotion Employee Event: {}", record.value());
    Mono<EmployeeQueryDTO> employeeMono = employeeWebClientService.getEmployeeById(record.value().getEmployeeId());
    Mono<EmployeeQueryDTO> leaderMono = employeeWebClientService.getEmployeeById(record.value().getLeaderId());

    employeeMono.zipWith(leaderMono)
        .flatMap(tuple -> {
          EmployeeQueryDTO employee = tuple.getT1();
          EmployeeQueryDTO leader = tuple.getT2();

          PromotionDocument promotionDocument = promotionMapper.toPromotionDocument(
              employee,
              leader,
              record.value().getProposedLevel(),
              record.value().getStatus(),
              record.value().getRequestDate(),
              record.value().getDecisionDate(),
              record.value().getLeaderComments(),
              record.value().getPeriod()
          );

          return promotionRepository.save(promotionDocument);
        })
        .subscribe(
            savedPromotion -> {
              log.info("### Promotion saved: {}", savedPromotion);
              ack.acknowledge();
            },
            error -> log.error("Error processing promotion: {}", error.getMessage())
        );
  }
}
