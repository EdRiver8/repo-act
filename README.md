# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por �ltimo el inicio y configuraci�n de la aplicaci�n.

Lee el art�culo [Clean Architecture � Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el m�dulo m�s interno de la arquitectura, pertenece a la capa del dominio y encapsula la l�gica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este m�dulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define l�gica de aplicaci�n y reacciona a las invocaciones desde el m�dulo de entry points, orquestando los flujos hacia el m�dulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no est�n arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
gen�ricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patr�n de dise�o [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicaci�n o el inicio de los flujos de negocio.

## Application

Este m�dulo es el m�s externo de la arquitectura, es el encargado de ensamblar los distintos m�dulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma autom�tica, inyectando en �stos instancias concretas de las dependencias declaradas. Adem�s inicia la aplicaci�n (es el �nico m�dulo del proyecto donde encontraremos la funci�n �public static void main(String[] args)�.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**

---

## Base de Datos - PostgreSQL

Este proyecto utiliza **PostgreSQL** como base de datos principal. La configuración de H2 (base de datos en memoria) ha sido comentada.

### 🚀 Inicio Rápido con Docker

```bash
# Iniciar PostgreSQL con Docker Compose
docker-compose up -d postgres

# Ver logs
docker-compose logs -f postgres

# Detener
docker-compose stop
```

### 🛠️ Script de Gestión (PowerShell)

Un script de PowerShell está disponible para facilitar la gestión:

```powershell
# Ver comandos disponibles
.\db-manager.ps1 help

# Iniciar base de datos
.\db-manager.ps1 start

# Ver estado
.\db-manager.ps1 status

# Conectar a PostgreSQL
.\db-manager.ps1 connect
```

### 📊 Configuración de Conexión

La configuración se encuentra en `application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/repo_activo
    username: postgres
    password: postgres
```

### 📚 Documentación Adicional

- [POSTGRESQL_SETUP.md](POSTGRESQL_SETUP.md) - Guía completa de configuración
- [docker-compose.yml](docker-compose.yml) - Configuración de contenedores
- PgAdmin disponible en: http://localhost:5050

### 🔍 Características

- **JSONB**: Campos `definicion_esquema`, `imagenes_s3` y `datos_dinamicos` usan JSONB para búsquedas eficientes
- **Índices GIN**: Búsquedas optimizadas en campos JSON
- **Auto-increment**: Uso de `BIGSERIAL` en PostgreSQL

### 🧪 Verificación

```bash
# Compilar y ejecutar
./gradlew bootRun

# Verificar endpoint
curl http://localhost:8080/ra/api/activos/tipo?tipo_activo=EQUIPO_COMPUTO
```
