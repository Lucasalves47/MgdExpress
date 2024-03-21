package br.com.api.mgdexpress.MGD.EXPRESS.repository;

import br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput.DtoOrderid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandeShakeDisputRepository extends JpaRepository<DtoOrderid,String> {
    @Query("select h from DtoOrderid h where h.orderId in (select p.idPedidoIfood from Pedido p where p.gerente.id=:id)")
    List<DtoOrderid> buscarHandeShakeById(Long id);
}
