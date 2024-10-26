storage "file" {
  path = "/vault/file"
}

listener "tcp" {
  address     = "0.0.0.0:8201"
  tls_disable = 1
}

ui = true
disable_mlock = true

# Configurar tiempos de lease (opcional)
default_lease_ttl = "168h"
max_lease_ttl = "720h"

# Agregar api_addr para evitar advertencias y problemas con las conexiones externas
api_addr = "http://0.0.0.0:8201"
