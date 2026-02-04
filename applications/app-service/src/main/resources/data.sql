INSERT INTO activos (tipo_activo, grupo, subgrupo, tipo_producto, id_activo_padre, condicion, descripcion, compania_duena, ciudad_ubicacion, marca, id_estado, definicion_esquema, imagenes_s3) VALUES
('EQUIPO_COMPUTO', 1, 101, 1001, NULL, 'NUEVO', 'Laptop Dell Latitude 5520', 'Bancolombia', 'Bogota', 'Dell', 1, 
 '[{"campo":"procesador","tipo":"string","valor":"Intel Core i7"},{"campo":"ram","tipo":"string","valor":"16GB"}]', 
 '[{"url":"https://s3.amazonaws.com/imagen1.jpg","descripcion":"Vista frontal"}]'),
('EQUIPO_COMPUTO', 1, 101, 1001, NULL, 'USADO', 'Laptop HP EliteBook 840', 'Bancolombia', 'Medellin', 'HP', 1, 
 '[{"campo":"procesador","tipo":"string","valor":"Intel Core i5"},{"campo":"ram","tipo":"string","valor":"8GB"}]', 
 '[{"url":"https://s3.amazonaws.com/imagen2.jpg","descripcion":"Vista frontal"}]'),
('MOBILIARIO', 2, 201, 2001, NULL, 'BUENO', 'Escritorio ejecutivo', 'Bancolombia', 'Cali', 'Herman Miller', 1, 
 '[{"campo":"material","tipo":"string","valor":"Madera"},{"campo":"dimensiones","tipo":"string","valor":"150x80cm"}]', 
 '[{"url":"https://s3.amazonaws.com/imagen3.jpg","descripcion":"Vista completa"}]'),
('VEHICULO', 3, 301, 3001, NULL, 'EXCELENTE', 'Camioneta Chevrolet Captiva', 'Bancolombia', 'Barranquilla', 'Chevrolet', 1, 
 '[{"campo":"modelo","tipo":"string","valor":"2022"},{"campo":"placa","tipo":"string","valor":"ABC123"}]', 
 '[{"url":"https://s3.amazonaws.com/imagen4.jpg","descripcion":"Vista exterior"}]'),
('EQUIPO_COMPUTO', 1, 101, 1002, NULL, 'NUEVO', 'Monitor Samsung 27 pulgadas', 'Bancolombia', 'Bogota', 'Samsung', 1, 
 '[{"campo":"resolucion","tipo":"string","valor":"2560x1440"},{"campo":"tipo","tipo":"string","valor":"LED"}]', 
 '[{"url":"https://s3.amazonaws.com/imagen5.jpg","descripcion":"Vista frontal"}]');

INSERT INTO historial_estados (id_activo, id_estado, fecha, usuario_responsable) VALUES
(1, 1, CURRENT_TIMESTAMP(), 'admin@bancolombia.com'),
(2, 1, CURRENT_TIMESTAMP(), 'admin@bancolombia.com'),
(3, 1, CURRENT_TIMESTAMP(), 'admin@bancolombia.com'),
(4, 1, CURRENT_TIMESTAMP(), 'admin@bancolombia.com'),
(5, 1, CURRENT_TIMESTAMP(), 'admin@bancolombia.com');

INSERT INTO detalles_activos (id_activo, datos_dinamicos) VALUES
(1, '[{"campo":"numero_serie","valor":"LAT-2024-001"},{"campo":"fecha_compra","valor":"2024-01-15"},{"campo":"garantia_meses","valor":"36"}]'),
(2, '[{"campo":"numero_serie","valor":"HP-2023-045"},{"campo":"fecha_compra","valor":"2023-06-20"},{"campo":"garantia_meses","valor":"24"}]'),
(3, '[{"campo":"numero_inventario","valor":"MOB-2024-100"},{"campo":"fecha_adquisicion","valor":"2024-02-01"},{"campo":"ubicacion_piso","valor":"Piso 3"}]'),
(4, '[{"campo":"numero_placa","valor":"ABC123"},{"campo":"modelo","valor":"2022"},{"campo":"kilometraje","valor":"25000"}]'),
(5, '[{"campo":"numero_serie","valor":"SAM-MON-2024-020"},{"campo":"fecha_compra","valor":"2024-01-10"},{"campo":"garantia_meses","valor":"12"}]');