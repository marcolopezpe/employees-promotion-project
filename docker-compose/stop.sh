#!/bin/bash

echo ''
echo '###########################################################'
echo 'Eliminacion del stack de contenedores"'
echo '###########################################################'

docker compose -f 01-pg-employee.yml \
							 -f 02-pg-performance-review.yml \
							 -f 03-pg-human-resources.yml \
							 -f 04-kafka-server.yml \
							 -f 05-redis-server.yml \
							 -f 06-mongo-promotion.yml \
							 -f 07-zipkin-server.yml \
							 -f 08-grafana-prometheus-server.yml \
							 -f 09-hashicorp-vault-server.yml \
							 -f 10-jasper-server.yml \
				-p employees-promotion-app \
				down

echo ''
echo '#########################################################################'
echo 'Eliminacion de la red para todos los contenedos "employees-promotion-net"'
echo '#########################################################################'

docker network rm employees-promotion-net
