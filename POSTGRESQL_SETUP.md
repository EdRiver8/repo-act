# Configuración de PostgreSQL para Repo-Activo

Este documento describe cómo configurar PostgreSQL para el proyecto después de migrar desde H2.

## 📋 Cambios Realizados

### 1. Configuración (`application.yaml`)

- ✅ Comentada configuración de H2
- ✅ Agregada configuración de PostgreSQL
- ✅ Actualizado dialect de Hibernate a PostgreSQL
- ✅ Configurado pool de conexiones HikariCP

### 2. Dependencias (`build.gradle`)

- ✅ Comentada dependencia de H2
- ✅ Agregada dependencia de PostgreSQL driver

### 3. Schema SQL

- ✅ Cambiado `AUTO_INCREMENT` → `BIGSERIAL`
- ✅ Cambiado `JSON` → `JSONB`
- ✅ Agregados índices GIN para búsquedas JSONB

### 4. Datos de Prueba

- ✅ Actualizado `CURRENT_TIMESTAMP()` → `CURRENT_TIMESTAMP`
- ✅ Agregado cast `::jsonb` para valores JSONB

### 5. Entidades JPA

- ✅ Actualizados DAOs para usar `columnDefinition = "jsonb"`
- ✅ Actualizada query nativa con sintaxis PostgreSQL

## 🚀 Instalación de PostgreSQL

### Opción 1: Docker (Recomendado)

```bash
# Crear y ejecutar contenedor PostgreSQL
docker run --name postgres-repo-activo \
  -e POSTGRES_DB=repo_activo \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:15

# Verificar que está corriendo
docker ps
```

### Opción 2: Instalación Local

1. Descargar PostgreSQL desde: https://www.postgresql.org/download/windows/
2. Instalar siguiendo el wizard
3. Crear base de datos:

```sql
CREATE DATABASE repo_activo;
```

## ⚙️ Configuración de Conexión

La configuración actual en `application.yaml`:

```yaml
spring:
  datasource:
    url: "jdbc:postgresql://localhost:5432/repo_activo"
    username: "postgres"
    password: "postgres"
```

### Variables de entorno (Producción)

Para producción, usa variables de entorno:

```yaml
spring:
  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5432/repo_activo}
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:postgres}
```

## 🔧 Primera Ejecución

1. **Verificar PostgreSQL activo:**

   ```bash
   # Si usas Docker
   docker ps

   # O desde PowerShell
   Get-Service postgresql*
   ```

2. **Ejecutar la aplicación:**

   ```bash
   ./gradlew bootRun
   ```

3. **Verificar tablas creadas:**
   ```sql
   \c repo_activo
   \dt
   ```

## 📊 Verificación de Datos

Conectarse a PostgreSQL y verificar:

```sql
-- Contar registros
SELECT COUNT(*) FROM activos;
SELECT COUNT(*) FROM historial_estados;
SELECT COUNT(*) FROM detalles_activos;

-- Ver datos JSONB
SELECT
    id_activo,
    tipo_activo,
    definicion_esquema->>'campo' as campo
FROM activos
WHERE definicion_esquema @> '{"campo":"procesador"}';
```

## 🔍 Consultas JSONB Útiles

```sql
-- Buscar en JSONB con operador de contención
SELECT * FROM activos
WHERE definicion_esquema @> '{"campo":"procesador"}';

-- Extraer valor de JSONB
SELECT
    tipo_activo,
    definicion_esquema->>0 as primer_elemento
FROM activos;

-- Búsqueda de texto en JSONB
SELECT * FROM detalles_activos
WHERE datos_dinamicos::text LIKE '%LAT-2024%';
```

## 🐛 Troubleshooting

### Error: "org.postgresql.Driver not found"

```bash
./gradlew clean build
```

### Error: "Connection refused"

- Verificar que PostgreSQL esté corriendo
- Verificar puerto 5432 disponible
- Revisar firewall

### Error: "Database 'repo_activo' does not exist"

```sql
CREATE DATABASE repo_activo;
```

## 📝 Notas Adicionales

### Diferencias H2 vs PostgreSQL

| Característica | H2                    | PostgreSQL          |
| -------------- | --------------------- | ------------------- |
| Auto-increment | `AUTO_INCREMENT`      | `SERIAL/BIGSERIAL`  |
| JSON           | `JSON`                | `JSONB`             |
| Timestamp      | `CURRENT_TIMESTAMP()` | `CURRENT_TIMESTAMP` |
| String concat  | `CONCAT()`            | `\|\|`              |
| Cast           | `CAST(x AS VARCHAR)`  | `x::text`           |

### Índices GIN

Los índices GIN (Generalized Inverted Index) permiten búsquedas eficientes en JSONB:

```sql
CREATE INDEX idx_name ON table USING GIN (jsonb_column);
```

### Backup y Restore

```bash
# Backup
pg_dump -U postgres repo_activo > backup.sql

# Restore
psql -U postgres repo_activo < backup.sql
```

## ✅ Checklist Post-Migración

- [ ] PostgreSQL instalado y corriendo
- [ ] Base de datos `repo_activo` creada
- [ ] Dependencias actualizadas (`./gradlew build`)
- [ ] Aplicación inicia sin errores
- [ ] Tablas creadas correctamente
- [ ] Datos de prueba insertados
- [ ] Endpoints funcionando
- [ ] Tests pasando

## 🔗 Referencias

- [PostgreSQL JSON Functions](https://www.postgresql.org/docs/current/functions-json.html)
- [Spring Boot PostgreSQL](https://spring.io/guides/gs/accessing-data-jpa/)
- [JSONB vs JSON](https://www.postgresql.org/docs/current/datatype-json.html)
