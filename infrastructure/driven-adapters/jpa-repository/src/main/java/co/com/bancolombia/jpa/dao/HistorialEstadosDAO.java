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
    @Column(name = "id_historial_estado")
    private Long idHistorialEstado;

    @Column(name = "id_activo")
    private Long idActivo;
    
    @Column(name = "id_estado")
    private Integer idEstado;

    private LocalDateTime fecha;

    @Column(name = "usuario_responsable")
    private String usuarioResponsable;
}
