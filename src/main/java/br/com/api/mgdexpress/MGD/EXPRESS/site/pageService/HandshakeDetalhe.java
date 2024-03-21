package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

import br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput.DtoOrderid;
import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Pedido;

public class HandshakeDetalhe {

    public static String html(String url, Pedido pedido, DtoOrderid handShake){
        String bandeiraDoCartao = "";
        var imgs = new StringBuilder();
        var alternatives = new StringBuilder();
        if(pedido.getCard() != null){
            bandeiraDoCartao = "bandeira do cartao :"+pedido.getCard();
        }

        handShake.getMetadata().getMetadata().getEvidences().forEach(img->{
            imgs.append("<a href=\" " +img.getUrl()+"\" class=\"Ref\"><img class=\"img\"\n" +
                    "                                        src=\" "+img.getUrl()+"\" alt=\"\"></a>");
        });

        handShake.getMetadata().getAlternatives().forEach(alternative -> {
            alternatives.append(" <input type=\"radio\" class=\"valorReenbouso\" name=\"valores\" value=\" "+alternative.getMetadata().getMaxAmount().getValue()+"\">\n" +
                    "            <label for=\"valorReenbouso\">"+alternative.getMetadata().getMaxAmount().getValue()+"</label>");
        });


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
                                  
                                          .ifood {
                                              display: flex;
                                  
                                          }
                                  
                                          button {
                                              background-color: #4CAF50;
                                              color: white;
                                              padding: 10px 20px;
                                              border: none;
                                              border-radius: 5px;
                                              cursor: pointer;
                                              font-size: 16px;
                                              margin-left: 15px;
                                  
                                          }
                                  
                                          .btnAceitar {
                                              margin-left: 40px;
                                              margin-right: auto;
                                          }
                                  
                                          .btnRejeitar {
                                              margin-right: 40px;
                                              margin-left: auto;
                                          }
                                  
                                          .btnRejeitar:hover {
                                              background-color: red;
                                          }
                                  
                                          .btnAceitar:hover {
                                              background-color: #008704;
                                          }
                                  
                                          .btncontraProposta {
                                              margin-left: 1%;
                                              margin-right: 1%;
                                          }
                                  
                                          .btncontraProposta:hover {
                                              background-color: rgb(25, 25, 211);
                                          }
                                  
                                          .itensIfood {
                                              display: flex;
                                          }
                                  
                                          .itensIfood p {
                                              margin-left: 8%;
                                  
                                          }
                                  
                                          .popup {
                                              display: none;
                                              /* Hidden by default */
                                              position: fixed;
                                              /* Stay in place */
                                              z-index: 1;
                                              /* Sit on top */
                                              left: 0;
                                              top: 0;
                                              width: 100%;
                                              /* Full width */
                                              height: 100%;
                                              /* Full height */
                                              overflow: auto;
                                              /* Enable scroll if needed */
                                              background-color: rgba(0, 0, 0, 0.4);
                                              /* Black w/ opacity */
                                          }
                                  
                                          .popup-content {
                                              background-color: #fefefe;
                                              margin: 15% auto;
                                              padding: 20px;
                                              border: 1px solid #888;
                                              width: 80%;
                                          }
                                  
                                          .close {
                                              color: #aaa;
                                              float: right;
                                              font-size: 28px;
                                              font-weight: bold;
                                          }
                                  
                                          .close:hover,
                                          .close:focus {
                                              color: black;
                                              text-decoration: none;
                                              cursor: pointer;
                                          }
                                  
                                          .popup-content .dadospopup {
                                              margin-left: auto;
                                              margin-right: auto;
                                              display: block;
                                          }
                                  
                                          .popup1 {
                                              display: none;
                                              /* Hidden by default */
                                              position: fixed;
                                              /* Stay in place */
                                              z-index: 1;
                                              /* Sit on top */
                                              left: 0;
                                              top: 0;
                                              width: 100%;
                                              /* Full width */
                                              height: 100%;
                                              /* Full height */
                                              overflow: auto;
                                              /* Enable scroll if needed */
                                              background-color: rgba(0, 0, 0, 0.4);
                                              /* Black w/ opacity */
                                          }
                                  
                                          .popup-content1 {
                                              background-color: #fefefe;
                                              margin: 15% auto;
                                              padding: 20px;
                                              border: 1px solid #888;
                                              width: 80%;
                                          }
                                  
                                          .close1 {
                                              color: #aaa;
                                              float: right;
                                              font-size: 28px;
                                              font-weight: bold;
                                          }
                                  
                                          .close1:hover,
                                          .close1:focus {
                                              color: black;
                                              text-decoration: none;
                                              cursor: pointer;
                                          }
                                  
                                        
                                  
                                          .imagens {
                                              display: flex;
                                              overflow: auto;
                                              background-color: #c9c9c9;
                                              border-radius: 4px;
                                  
                                          }
                                  
                                          ::-webkit-scrollbar {
                                              width: 4px;
                                              /* Largura da barra de rolagem vertical */
                                              height: 4px;
                                              /* Altura da barra de rolagem horizontal */
                                          }
                                  
                                          /* Para navegadores baseados no WebKit */
                                          ::-webkit-scrollbar-thumb {
                                              background-color: #888;
                                              /* Cor do controle de rolagem */
                                              border-radius: 5px;
                                              /* Borda do controle de rolagem */
                                          }
                                  
                                          /* Para navegadores baseados no Gecko */
                                          ::-moz-scrollbar {
                                              width: 4px;
                                              /* Largura da barra de rolagem vertical */
                                              height: 4px;
                                              /* Altura da barra de rolagem horizontal */
                                          }
                                  
                                          /* Para navegadores baseados no Gecko */
                                          ::-moz-scrollbar-thumb {
                                              background-color: #888;
                                              /* Cor do controle de rolagem */
                                              border-radius: 5px;
                                              /* Borda do controle de rolagem */
                                          }
                                  
                                          .img {
                                              height: 300px;
                                              width: 350px;
                                              margin: 1px;
                                              border-radius: 3px;
                                          }
                                  
                                          textarea {
                                              width: 60%;
                                              height: 100px;
                                          }
                                  
                                          .popup p {
                                              font-size: small;
                                              color: #888;
                                              margin-top: 0;
                                          }
                                  
                                          .popup h4 {
                                              margin-bottom: 2px;
                                          }
                                      </style>
                                  
                                      <script>
                                  
                                          function openPop() {
                                              var pop = document.getElementById("popup")
                                              pop.style.display = "block"
                                  
                                          }
                                  
                                          function closePop() {
                                              var pop = document.getElementById("popup")
                                              pop.style.display = "none"
                                          }
                                  
                                          function openPop1() {
                                              var pop = document.getElementById("popup1")
                                              pop.style.display = "block"
                                  
                                          }
                                  
                                          function closePop1() {
                                              var pop = document.getElementById("popup1")
                                              pop.style.display = "none"
                                          }
                                  
                                          function buscarValor() {
                                              var id = document.getElementById("proposta")
                                              var REFUND_BENEFIT = document.querySelector("input[name='refund_bene']:checked")
                                  
                                              if (REFUND_BENEFIT) {
                                                  console.log(id.value)
                                                  console.log(REFUND_BENEFIT.value)
                                                  contraProposta('"""+handShake.getId()+"',id.value,REFUND_BENEFIT.value)"+"""
                                              }
                                          }
                                  
                                  
                                          function rejeitarPropostaDetalhe(){
                                              var reaso = document.getElementsByName("reaso")
                                  
                                              regeitarProposta('"""+handShake.getId()+"',reaso.value())"+ """
                                  
                                  
                                          }
                                      </script>
                                  </head>
                                  
                                  <body>
                                      <nav>
                                          <div>
                                              <p class="backLink" onclick='carregarPagina("+url+/site/gerente/home")'>
                                                  ←</p>
                                          </div>
                                          <h2>Detalhe do Cancelamento</h2>
                                      </nav>
                                  
                                      <main>
                                          <div class="card">
                                              <h3>Cancelamento Total</h3>
                                              <p><strong>Menssagen:</strong>"""+handShake.getMetadata().getMessage()+"""
                                              </p>
                                              <h3>Pedido</h3>
                                              <p><strong>Local de Destino : </strong>"""+pedido.getLocalDestino()+"""
                                              </p>
                                              <p><strong>Valor :</strong>"""+pedido.getValorDoPedido()+"""
                                              </p>
                                              """+pedido.getBeneficios()+ """
                                  
                                              <p><strong>Metodo De Pagamento :</strong>"""+pedido.getMetodoPagamento()+"""
                                              </p>
                                              <p><strong>Bandeira:</strong>"""+bandeiraDoCartao+"""
                                              </p>
                                              <p><strong>Troco :</strong>"""+pedido.getTroco()+"""
                                              </p>
                                  
                                  
                                  
                                  
                                              <h3>Imagens</h3>
                                              <div class="imagens">
                                                  """+imgs+"""
                                              </div>
                                  
                                              <h3>Itens</h3>
                                  
                                              """+pedido.getItensDoPedido()+"""
                                  
                                  
                                  
                                              <h3>Reembolso</h3>
                                  
                                              """+alternatives+"""
                                  
                                              <h3>Tipo do reembolso</h3>
                                              <input type="radio" class="refund/bene" name="refund_bene" value="BENEFIT">
                                              <label for="refund/bene">BENEFICIO</label>
                                  
                                              <input type="radio" class="refund/bene" name="refund_bene" value="REFUND">
                                              <label for="refund/bene">REEMBOLSO</label>
                                  
                                              <input type="radio" class="refund/bene" name="refund_bene" value="ADDITIONAL_TIME">
                                              <label for="refund/bene">horas adicionais</label>
                                         
                                  
                                  
                                          </div>
                                          <div class="ifood">
                                              <button class="btnRegeitarNegociaçao" onclick="openPop()">cancelar</button>
                                              <button class="btncontraProposta" onclick="openPop1()">Contra-Proposta</button>
                                              <button class="btnAceitarProposta" onclick="aceitarProposta('"""+handShake.getId()+"')\">Aceitar</button>"+"""
                                  
                                          </div>
                                  
                                          <div id="popup" class="popup">
                                              <!-- Popup content -->
                                              <div class="popup-content">
                                                  <span class="close" id="closePopup" onclick="closePop()">&times;</span>
                                  
                                                  <h4>motivo:</h4>
                                                  <p>Descreva abaixo o motivo de não aceitar a proposta.</p>
                                                  <textarea name="reaso"></textarea> <br>
                                  
                                                
                                                   <button id="enviarBtn" class="btnRejeitar" onclick="rejeitarPropostaDetalhe()">Enviar Cancelamento</button>
                                  
                                              </div>
                                              </div>
                                  
                                              <div id="popup1" class="popup1">
                                                  <!-- Popup content -->
                                                  <div class="popup-content1">
                                                      <span class="close1" id="closePopup1" onclick="closePop1()">&times;</span>
                                     
                                                      <h4>Valor:</h4>
                                                          <input type="text" id="proposta" />
                                     
                                                       <button id="enviarBtn1" class="btnRejeitar1" onclick="buscarValor()">Enviar proposta</button>
                                     
                                                  </div>
                                  
                                  
                                      </main>
                                  
                                  </body>
                                  
                                  
                                  
                                  </html>
                """;
    }
}
