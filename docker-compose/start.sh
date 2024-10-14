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
							 -f 02-pg-performance-review.yml \
							 -f 03-pg-human-resources.yml \
							 -f 04-kafka-server.yml \
							 -f 05-redis-server.yml \
							 -f 06-mongo-promotion.yml \
							 -f 07-zipkin-server.yml \
							 -f 08-grafana-prometheus-server.yml \
				-p employees-promotion-app \
				up -d

echo ''
echo '###########################################################'
echo 'Creacion de los topicos de kafka'
echo '###########################################################'

docker exec -it kafka-broker-1 kafka-topics --bootstrap-server localhost:19092 --if-not-exists --create --topic topic-eligible-employees --partitions 1 --replication-factor 1
docker exec -it kafka-broker-1 kafka-topics --bootstrap-server localhost:19092 --if-not-exists --create --topic topic-reject-employees --partitions 1 --replication-factor 1
docker exec -it kafka-broker-1 kafka-topics --bootstrap-server localhost:19092 --if-not-exists --create --topic topic-promotion-employees --partitions 1 --replication-factor 1

#kafka-avro-console-producer --broker-list kafka-broker-1:9092 --topic topic-eligible-employees --property schema.registry.url=http://schema-registry:8081 --property value.schema='{"type":"record","name":"logLineForward","fields":[{"name":"ip","type":"string"}]}'
