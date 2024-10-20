#!/bin/bash

echo ''
echo '###########################################################'
echo 'Eliminacion del stack de contenedores"'
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
				down

echo ''
echo '#########################################################################'
echo 'Red "employees-promotion-net" eliminada'
echo '#########################################################################'

docker network rm employees-promotion-net
