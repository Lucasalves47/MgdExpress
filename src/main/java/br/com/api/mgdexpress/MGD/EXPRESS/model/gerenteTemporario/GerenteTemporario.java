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
    private Localizacao localizacao;
    private String merchatId;
    private String cnpj;

    public GerenteTemporario(DtoDadosGerente dtoDadosGerente, DtoGerenteTemporario dtoGerenteTemporario) {
        var endereco = dtoDadosGerente.getAddress();
        this.nome = dtoDadosGerente.getName();
        this.telefone = dtoGerenteTemporario.telefone();
        this.email = dtoGerenteTemporario.email();
        this.senha = dtoGerenteTemporario.senha();
        this.nomeEstebelecimento = dtoDadosGerente.getCorporateName();
        this.localEstabelecimento = endereco.getCountry()+",  "+endereco.getState()+",  "+endereco.getCity()+",  "+endereco.getStreet()+",  "+endereco.getNumber()+",  "+endereco.getPostalCode();
        this.localizacao = new Localizacao(dtoDadosGerente.getAddress().getLongitude()+"",dtoDadosGerente.getAddress().getLatitude()+"");
        this.merchatId = dtoDadosGerente.getId();
        this.cnpj = dtoGerenteTemporario.cnpj();
    }

    public GerenteTemporario(DtoGerenteTemporarioSEmIfood dadosGerente) {
        this.nome = dadosGerente.nome();
        this.telefone = dadosGerente.telefone();
        this.email = dadosGerente.email();
        this.senha = dadosGerente.senha();
        this.nomeEstebelecimento = dadosGerente.nomeEstabelecimento();
        this.localEstabelecimento = dadosGerente.localEstabelecimento();
        this.localizacao = new Localizacao(dadosGerente.lng(), dadosGerente.lat());
        this.merchatId = dadosGerente.merchatId();
        this.cnpj = dadosGerente.cnpj();
    }
}
