#!/bin/bash

echo ''
echo '###########################################################'
echo 'Eliminacion del stack de microservicios'
echo '###########################################################'

docker compose -f 41-microservices.yml \
				-p employees-promotion-app \
				down