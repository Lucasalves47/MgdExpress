package br.com.api.mgdexpress.MGD.EXPRESS.repository;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MotoboyRepository extends JpaRepository<Motoboy,Long> {


    @Query("select m from Motoboy m where m.ativo = true")
    List<Motoboy> findAllAtivos();


    @Query("select m from Motoboy m where m.id in(select p.motoboy.id from Pedido p where p.status = ANDAMENTO and p.gerente.email =:email)")
    Page<Motoboy> findAllAtivosEmEntregaByGerente(Pageable page,String email);

    @Query("select m from Motoboy m where m.disponivel = true")
    List<Motoboy> findAllDisponivelTrue();


    Motoboy findByEmail(String username);

    @Query("SELECT MAX(e.id) FROM Motoboy e")
    Long encontrarMaiorId();

}
