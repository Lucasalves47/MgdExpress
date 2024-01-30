package br.com.api.mgdexpress.MGD.EXPRESS.repository;

import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Pedido;
import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Status;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    @Query("select p from Pedido p where p.status = INICIAR and p.motoboy.id = :id")
    List<Pedido> findAllWhereStatusINICIAR(Long id);

    @Query("select p from Pedido p where p.status = INICIAR and p.gerente.email = :email and p.motoboy = NULL")
    List<Pedido> findAllWhereStatusINICIARByLogin(String email);

    @Query("select p from Pedido p where p.motoboy.email = :email")
    List<Pedido> findByEmailMotoboy(String email);

    @Query("select p from Pedido p where p.status = ANDAMENTO and p.id not in (select h.pedidoId from Historico h)")
    Page<Pedido> findAllWhereStatusANDAMENTO(Pageable page);
    @Query("select p from Pedido p where p.id = :id and p.id not in (select h.pedidoId from Historico h)")
    Pedido getReferenceByIdAndNotinHistorico(Long id);
}
