package br.com.api.mgdexpress.MGD.EXPRESS.model.gerenteTemporario;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.DadosGerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.DtoGerenteTemporario;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Localizacao;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.DtoDadosGerente;
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
@Table(name = "gerenteTemporario")
public class GerenteTemporario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private String nomeEstebelecimento;
    private String localEstabelecimento;
    private String clientId;
    private String clientSecret;
    private Localizacao localizacao;

    public GerenteTemporario(DtoDadosGerente dtoDadosGerente, DtoGerenteTemporario dtoGerenteTemporario) {
        var endereco = dtoDadosGerente.getAddress();
        this.nome = dtoDadosGerente.getName();
        this.telefone = dtoGerenteTemporario.telefone();
        this.email = dtoGerenteTemporario.email();
        this.senha = dtoGerenteTemporario.senha();
        this.nomeEstebelecimento = dtoDadosGerente.getCorporateName();
        this.localEstabelecimento = endereco.getCountry()+",  "+endereco.getState()+",  "+endereco.getCity()+",  "+endereco.getStreet()+",  "+endereco.getNumber()+",  "+endereco.getPostalCode();
        this.clientId = dtoGerenteTemporario.clientId();
        this.clientSecret = dtoGerenteTemporario.clientSecret();
        this.localizacao = new Localizacao(dtoDadosGerente.getAddress().getLongitude()+"",dtoDadosGerente.getAddress().getLatitude()+"");

    }
}
