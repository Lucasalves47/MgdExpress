package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Pedido;

public class DetalhePedido {

    public static String detalhar(Pedido dados){
        return """
                
                      <!DOCTYPE html>
                              <html lang="pt-br">
                              
                              <head>
                                  <meta charset="UTF-8">
                                  <title>Detalhe do Histórico</title>
                                  <style>
                                      body {
                                          font-family: Arial, sans-serif;
                                          margin: 0;
                                          padding: 0;
                                          background-color: #f4f4f4;
                                          color: #333;
                                      }
                              
                                      nav {
                                          background-color: #333;
                                          color: white;
                                          padding: 10px;
                                          text-align: center;
                                          display: flex;
                                          align-items: center;
                                      }
                              
                                      main {
                                          padding: 20px;
                                      }
                              
                                      .card {
                                          background-color: #fff;
                                          border-radius: 8px;
                                          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                                          margin-bottom: 20px;
                                          padding: 20px;
                                      }
                              
                                      .card p {
                                          margin: 0;
                                          margin-bottom: 10px;
                                      }
                              
                                      .card h3 {
                                          margin-top: 0;
                                          color: #4caf50;
                                      }
                              
                                      .backLink {
                                          cursor: pointer;
                                      }
                              
                                      h2 {
                                          margin-left: 40%;
                                          color: #fff;
                                          
                                      }
                              
                                      ul {
                                          list-style-type: none;
                                          padding: 0;
                                      }
                              
                                      li {
                                          margin-bottom: 10px;
                                      }
                                  </style>
                              </head>
                              
                              <body>
                                  <nav>
                                      <div>
                                          <p class="backLink" onclick='listarPedidos()'>←</p>
                                      </div>
                                      <h2>Detalhe do Pedido</h2>
                                  </nav>
                              
                                  <main>
                                      <div class="card">
                                          <h3>Pedido</h3>
                                          <p>Nome do Estabelecimento: """ + dados.getNomeEstabelecimento() + """
                                          </p>
                                          <p>Local de Origem : """ + dados.getLocalOrigem() + """
                                          </p>
                                          <p>Local de Destino : """ + dados.getLocalDestino() + """
                                          </p>
                                          <p>Valor : """ + dados.getValor() + """
                                          </p>
                                          <p>Observação : """ + dados.getObservacao() + """
                                          </p>
                                          <p>Itens do Pedido : """ + dados.getItensDoPedido() + """
                                          </p>
                                          <p>Data de Criação :  """ + dados.getDataCriacao() + """
                                          </p>
                           
                                          <h3>Gerente:</h3>
                                          <ul>
                                              <li>Nome : """ + dados.getGerente().getNome() + """
                                              </li>
                                              <li>Telefone : """ + dados.getGerente().getTelefone() + """
                                              </li>
                                              <li>Email : """ + dados.getGerente().getEmail() + """
                                          </li>
                                          </ul>
                                      </div>
                                  </main>
                              </body>
                              
                              </html>
                              
                """;
    }
}
