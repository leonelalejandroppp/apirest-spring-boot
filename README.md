
# Test Spring Boot / Leonel Carrasco

Un pequeño proyecto (API REST) realizado con Spring boot + Spring Security, que contempla un Registro y Login de usuarios, además de contener un endpoint para el listado de estos.


## Deployment

Para poder levantar el proyecto se debe tener Docker instalado y ejecutar el siguiente comando

```bash
# Construir la imagen Docker
docker build -t apirest-spring .
```

Luego

```bash
# Ejecutar el contenedor
docker run -p 8080:8181 apirest-spring
```

En caso de que la imagen de error (por motivos de arquitectura del laptop), verificar el cambio de imagen del OpenJDK del Dockerfile

```bash
#FROM openjdk:17-jdk-alpine
#si no funciona la imagen, ocupar la de arriba
FROM thingsboard/openjdk17:bookworm-slim
```


## API Reference

#### Registro de usuarios

```http
  POST /auth/register
```

```json
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.com",
    "password": "Hunter2!",
    "phones" : [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]
}
```
#### Login de usuarios


```http
  POST /auth/login
```
```json
{
    "email": "juan@rodriguez.com",
    "password": "Hunter2!"
}
```

#### Listado de usuarios


```http
  GET /api/users
```

```bash
curl -X GET "http://localhost:8080/api/users" \
  -H "Authorization: Bearer token que retorna el login"

```



