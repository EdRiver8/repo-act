package co.com.bancolombia.jpa.repository;

import co.com.bancolombia.jpa.dao.HistorialEstadosDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface IHistorialEstadosJPARepository
    extends JpaRepository<HistorialEstadosDAO, Long>, QueryByExampleExecutor<HistorialEstadosDAO> {
}
