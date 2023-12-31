package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.DadosHistorico;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.Historico;

public class Detalhehistorico {

    public static String detalhar(Historico dados) {
        return """
                               
                       <head>
                                  <meta charset="UTF-8">
                                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                                      }
                                  </style>
                              </head>
                              <body>
                                  <nav>
                                      <h2>Detalhe do Histórico</h2>
                                  </nav>
                              
                                  <main>
                                      <div class="card" id="historico-details">
                                          <!-- Conteúdo será preenchido dinamicamente com JavaScript -->
                                      </div>
                                  </main>
                                  <script>preencherDetalhesHistorico()</script>
                       
                """;
    }
}
