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
import pe.marcolopez.apps.epp.ms.kafka.event.PromotionLeaderEvent;
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
    PromotionEmployeeEvent promotionEmployeeEvent = record.value();
    log.info("### consumePromotionByEmployee: {}", promotionEmployeeEvent);

    Mono<EmployeeQueryDTO> employeeMono = employeeWebClientService
        .getEmployeeById(promotionEmployeeEvent.getEmployeeId())
        .doOnError(throwable -> log.error(throwable.getMessage(), throwable));

    Mono<EmployeeQueryDTO> leaderMono = employeeWebClientService
        .getEmployeeById(promotionEmployeeEvent.getLeaderId())
        .doOnError(throwable -> log.error(throwable.getMessage(), throwable));

    employeeMono.zipWith(leaderMono)
        .flatMap(tuple -> {
          EmployeeQueryDTO employee = tuple.getT1();
          EmployeeQueryDTO leader = tuple.getT2();

          PromotionDocument promotionDocument = promotionMapper.toPromotionDocument(
              employee,
              leader,
              promotionEmployeeEvent.getProposedLevel(),
              promotionEmployeeEvent.getStatus(),
              promotionEmployeeEvent.getRequestDate(),
              promotionEmployeeEvent.getDecisionDate(),
              promotionEmployeeEvent.getLeaderComments(),
              promotionEmployeeEvent.getPeriod()
          );

          return promotionRepository.save(promotionDocument);
        })
        .subscribe(
            savedPromotion -> {
              log.info("### Promotion saved: {}", savedPromotion);
              ack.acknowledge();
            },
            error -> log.error("Error processing promotion: {}", error.toString())
        );
  }
  
  @KafkaListener(
      topics = "${kafka.consumer.topic-prom-leader-evaluate}",
      groupId = "${kafka.consumer.group-id}"
  )
  public void consumePromotionByLeader(ConsumerRecord<String, PromotionLeaderEvent> record,
                                         Acknowledgment ack) {
    PromotionLeaderEvent promotionLeaderEvent = record.value();
    log.info("### consumePromotionByLeader: {}", promotionLeaderEvent);

    Mono<EmployeeQueryDTO> employeeMono = employeeWebClientService
        .getEmployeeById(promotionLeaderEvent.getEmployeeId())
        .doOnError(throwable -> log.error(throwable.getMessage(), throwable));

    Mono<EmployeeQueryDTO> leaderMono = employeeWebClientService
        .getEmployeeById(promotionLeaderEvent.getLeaderId())
        .doOnError(throwable -> log.error(throwable.getMessage(), throwable));

    employeeMono.zipWith(leaderMono)
        .flatMap(tuple -> {
          EmployeeQueryDTO employee = tuple.getT1();
          EmployeeQueryDTO leader = tuple.getT2();

          PromotionDocument promotionDocument = promotionMapper.toPromotionDocument(
              employee,
              leader,
              promotionLeaderEvent.getProposedLevel(),
              promotionLeaderEvent.getStatus(),
              promotionLeaderEvent.getRequestDate(),
              promotionLeaderEvent.getDecisionDate(),
              promotionLeaderEvent.getLeaderComments(),
              promotionLeaderEvent.getPeriod()
          );

          return promotionRepository.save(promotionDocument);
        })
        .subscribe(
            savedPromotion -> {
              log.info("### Promotion saved: {}", savedPromotion);
              ack.acknowledge();
            },
            error -> log.error("Error processing promotion: {}", error.toString())
        );
  }
}
