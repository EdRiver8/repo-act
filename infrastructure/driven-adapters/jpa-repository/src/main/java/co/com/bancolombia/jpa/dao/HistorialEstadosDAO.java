package co.com.bancolombia.jpa.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_estados", schema = "public")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder

public class HistorialEstadosDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_historial_estado;

    private Long id_activo;
    private Integer id_estado;

    private LocalDateTime fecha;

    private String usuario_responsable;
}
