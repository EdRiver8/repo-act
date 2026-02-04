# ====================================
# PRUEBAS DEL MICROSERVICIO REPO-ACTIVO
# ====================================

Write-Host "`n=== Prueba 1: Buscar activos por tipo (EQUIPO_COMPUTO) ===" -ForegroundColor Cyan
try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/activos/tipo?tipo_activo=EQUIPO_COMPUTO" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $response.Content | ConvertFrom-Json | ConvertTo-Json -Depth 10
} catch {
    Write-Host "Error: $_" -ForegroundColor Red
}

Write-Host "`n=== Prueba 2: Buscar activos por tipo (MOBILIARIO) ===" -ForegroundColor Cyan
try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/activos/tipo?tipo_activo=MOBILIARIO" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $response.Content | ConvertFrom-Json | ConvertTo-Json -Depth 10
} catch {
    Write-Host "Error: $_" -ForegroundColor Red
}

Write-Host "`n=== Prueba 3: Guardar nuevo activo ===" -ForegroundColor Cyan
try {
    $nuevoActivo = @{
        tipoActivo = "EQUIPO_COMPUTO"
        grupo = 1
        subgrupo = 101
        tipoProducto = 1001
        condicion = "NUEVO"
        descripcion = "Laptop Lenovo ThinkPad"
        companiaDuena = "Bancolombia"
        ciudadUbicacion = "Bogota"
        marca = "Lenovo"
        idEstado = 1
    } | ConvertTo-Json

    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/activos/save" -Method POST -Body $nuevoActivo -ContentType "application/json"
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $response.Content
} catch {
    Write-Host "Error: $_" -ForegroundColor Red
}

Write-Host "`n=== Consola H2 ===" -ForegroundColor Cyan
Write-Host "URL: http://localhost:8080/h2" -ForegroundColor Yellow
Write-Host "JDBC URL: jdbc:h2:mem:test" -ForegroundColor Yellow
Write-Host "Usuario: sa" -ForegroundColor Yellow
Write-Host "Password: (vacio)" -ForegroundColor Yellow

Write-Host "`n=== Endpoints disponibles ===" -ForegroundColor Cyan
Write-Host "GET  /api/activos/tipo?tipo_activo={tipo}" -ForegroundColor Yellow
Write-Host "GET  /api/activos/modelo (con body JSON)" -ForegroundColor Yellow
Write-Host "POST /api/activos/save (con body Activos)" -ForegroundColor Yellow
Write-Host "POST /api/activos/guardarEstado (con body HistorialEstados)" -ForegroundColor Yellow
Write-Host "POST /api/activos/guardarDatos (con body DetallesActivos)" -ForegroundColor Yellow
