#!/bin/bash

echo ''
echo '###########################################################'
echo 'Creacion del stack de microservicios'
echo '###########################################################'

docker compose -f 41-microservices.yml \
				build

docker compose -f 41-microservices.yml \
				-p employees-promotion-app \
				up -d
