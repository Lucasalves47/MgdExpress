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
                                   margin-left: 30px;
                                   color: #fff;
                                   margin: 0;
                               }
                       
                               ul {
                                   list-style-type: none;
                                   padding: 0;
                               }
                       
                               li {
                                   margin-bottom: 10px;
                               }
                       
                               .inline-block {
                                   display: inline-block;
                               }
                       
                               .inline-block + p {
                                   margin-left: 20px;
                               }
                           </style>
                       </head>
                       
                       <body>
                           <nav>
                               <div>
                                   <p class="backLink" onclick='carregarPagina("https://mgdexpress-production.up.railway.app/site/gerente/home")'>←</p>
                               </div>
                               <h2>Detalhe do Histórico</h2>
                           </nav>
                       
                           <main>
                               <div class="card" id="historico-details">
                                   <h3>Histórico</h3>
                                   <p>Nome do Estabelecimento : """ + dados.getNomeEstabelecimento() + """
                                   </p>
                                   <p>Local de Origem :""" + dados.getLocalOrigem() + """
                                   </p>
                                   <p>Local de Destino : """ + dados.getLocalDestino() + """
                                   </p>
                                   <p>Valor : """ + dados.getValor() + """
                                   </p>
                                   <p>Observação : """ + dados.getObservacao() + """
                                   </p>
                                   <p>Itens do Pedido : """ + dados.getItensDoPedido() + """
                                   </p>
                                   <p class="inline-block">Data de Criação : """ + dados.getDataCriacao() + """
                                   </p>
                                   <p class="inline-block" style="margin-left: 20px;">Data de Entrega : """ + dados.getDataEntrega() + """
                                   </p>
                                   <h3>Motoboy</h3>
                                   <ul>
                                       <li>Nome : """ + dados.getMotoboy().getNome() + """
                                       </li>
                                       <li>Telefone : """ + dados.getMotoboy().getTelefone() + """
                                       </li>
                                       <li>Email : """ + dados.getMotoboy().getEmail() + """
                                   </li>
                                   </ul>
                                   <h3>Gerente </h3>
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
                       
                       
                       
                """;
    }
}
