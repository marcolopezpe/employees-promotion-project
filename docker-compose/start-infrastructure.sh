#!/bin/bash

echo ''
echo '########################################################################'
echo 'Creacion de la red para todos los contenedores "employees-promotion-net"'
echo '########################################################################'

docker network create --driver=bridge --subnet=172.9.0.0/16 employees-promotion-net

echo ''
echo '###########################################################'
echo 'Creacion del stack de contenedores'
echo '###########################################################'

docker compose -f 01-pg-employee.yml \
							 -f 02-pg-criteria.yml \
							 -f 03-pg-command-promotion.yml \
							 -f 04-kafka-server.yml \
							 -f 05-redis-server.yml \
							 -f 06-mongo-query-promotion.yml \
							 -f 07-zipkin-server.yml \
							 -f 08-grafana-prometheus-server.yml \
							 -f 09-hashicorp-vault-server.yml \
							 -f 10-jasper-server.yml \
				-p employees-promotion-app \
				up -d

echo ''
echo '###########################################################'
echo 'Creacion de los topicos de kafka'
echo '###########################################################'

docker exec -it kafka-broker-1 kafka-topics --bootstrap-server localhost:19092 --if-not-exists --create --topic topic-eligible-employee --partitions 1 --replication-factor 1
docker exec -it kafka-broker-1 kafka-topics --bootstrap-server localhost:19092 --if-not-exists --create --topic topic-evaluated-employee --partitions 1 --replication-factor 1

#kafka-avro-console-producer --broker-list kafka-broker-1:9092 --topic topic-eligible-employees --property schema.registry.url=http://schema-registry:8081 --property value.schema='{"type":"record","name":"logLineForward","fields":[{"name":"ip","type":"string"}]}'

echo ''
echo '###########################################################'
echo 'Creacion de los secretos en Hashicorp Vault'
echo '###########################################################'
docker exec -it vault vault kv put secret/ms-employee db-host=localhost db-name=db_employee db-password=PGEmployeePass123 db-port=15432 db-username=postgres
docker exec -it vault vault kv put secret/ms-criteria db-host=localhost db-name=db_criteria db-password=PGCriteriaPass123 db-port=25432 db-username=postgres
docker exec -it vault vault kv put secret/ms-command-promotion db-host=localhost db-name=db_command_promotion db-password=PGCommandPromotionPass123 db-port=35432 db-username=postgres
