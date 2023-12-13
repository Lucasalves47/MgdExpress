package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.Historico;
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
@Table(name ="motoboy")
public class Motoboy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String grauDeParentescoContatoEmergencia;
    private String telefoneEmergencia;
    private String chavePix;
    @Embedded
    private Localizacao localizacao;
    @OneToOne
    private Historico histico;
    private Boolean disponivel;
    private Boolean ativo;

    public Motoboy(DadosMotoboyCadastro dadosMotoboy) {
        this.nome = dadosMotoboy.nome();
        this.telefone = dadosMotoboy.telefone();
        this.email = dadosMotoboy.email();
        this.disponivel = true;
        this.ativo = true;
        this.cpf = dadosMotoboy.cpf();
        this.grauDeParentescoContatoEmergencia = dadosMotoboy.grauParentescoContatoEmergencia();
        this.telefoneEmergencia = dadosMotoboy.telefoneEmergencia();
        this.chavePix = dadosMotoboy.chavepix();
    }
}
