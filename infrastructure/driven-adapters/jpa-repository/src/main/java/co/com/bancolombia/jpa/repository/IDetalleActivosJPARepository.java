package co.com.bancolombia.jpa.repository;

import co.com.bancolombia.jpa.dao.DetalleActivosDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface IDetalleActivosJPARepository
    extends JpaRepository<DetalleActivosDAO, Long>, QueryByExampleExecutor<DetalleActivosDAO> {
}
