#!/bin/bash

echo ''
echo '###########################################################'
echo 'Acceso a Hashicorp Vault'
echo '###########################################################'
docker exec -it vault /bin/sh

vault login <token_json>

echo 'path "secret/ms-employee/" { capabilities = ["read", "create", "update", "delete", "list"] }' > my_policy.hcl
echo 'path "secret/ms-criteria/" { capabilities = ["read", "create", "update", "delete", "list"] }' >> my_policy.hcl
echo 'path "secret/ms-command-promotion/" { capabilities = ["read", "create", "update", "delete", "list"] }' >> my_policy.hcl

vault policy write my_policy my_policy.hcl
vault policy read my_policy
vault token create -policy=my_policy
export VAULT_TOKEN=<nuevo_token>

vault kv put secret/ms-employee db-host=localhost db-name=db_employee db-password=PGEmployeePass123 db-port=15432 db-username=postgres
vault kv put secret/ms-criteria db-host=localhost db-name=db_criteria db-password=PGCriteriaPass123 db-port=25432 db-username=postgres
vault kv put secret/ms-command-promotion db-host=localhost db-name=db_command_promotion db-password=PGCommandPromotionPass123 db-port=35432 db-username=postgres

#docker exec -it vault vault kv put secret/ms-employee db-host=localhost db-name=db_employee db-password=PGEmployeePass123 db-port=15432 db-username=postgres
#docker exec -it vault vault kv put secret/ms-criteria db-host=localhost db-name=db_criteria db-password=PGCriteriaPass123 db-port=25432 db-username=postgres
#docker exec -it vault vault kv put secret/ms-command-promotion db-host=localhost db-name=db_command_promotion db-password=PGCommandPromotionPass123 db-port=35432 db-username=postgres
