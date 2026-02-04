CREATE TABLE IF NOT EXISTS activos (
    id_activo BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_activo VARCHAR(255) NOT NULL,
    grupo INTEGER,
    subgrupo INTEGER,
    tipo_producto INTEGER,
    id_activo_padre BIGINT,
    condicion VARCHAR(255),
    descripcion TEXT,
    compania_duena VARCHAR(255),
    ciudad_ubicacion VARCHAR(255),
    marca VARCHAR(255),
    id_estado INTEGER NOT NULL,
    definicion_esquema JSON,
    imagenes_s3 JSON
);

CREATE TABLE IF NOT EXISTS historial_estados (
    id_historial_estado BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_activo BIGINT,
    id_estado INTEGER,
    fecha TIMESTAMP,
    usuario_responsable VARCHAR(255),
    FOREIGN KEY (id_activo) REFERENCES activos(id_activo)
);

CREATE TABLE IF NOT EXISTS detalles_activos (
    id_detalle_activo BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_activo BIGINT,
    datos_dinamicos JSON,
    FOREIGN KEY (id_activo) REFERENCES activos(id_activo)
);

CREATE INDEX IF NOT EXISTS idx_activos_tipo ON activos(tipo_activo);
CREATE INDEX IF NOT EXISTS idx_activos_estado ON activos(id_estado);
CREATE INDEX IF NOT EXISTS idx_historial_activo ON historial_estados(id_activo);
CREATE INDEX IF NOT EXISTS idx_detalles_activo ON detalles_activos(id_activo);