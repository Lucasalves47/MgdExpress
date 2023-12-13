package br.com.api.mgdexpress.MGD.EXPRESS.model.gerente;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerenteTemporario.GerenteTemporario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gerente")
public class Gerente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private String nomeEstebelecimento;
    private String localEstabelecimento;


    public Gerente(GerenteTemporario dadosGerente) {
        this.nome = dadosGerente.getNome();
        this.telefone = dadosGerente.getTelefone();
        this.email = dadosGerente.getEmail();
        this.nomeEstebelecimento = dadosGerente.getNomeEstebelecimento();
        this.localEstabelecimento = dadosGerente.getLocalEstabelecimento();
    }


}
