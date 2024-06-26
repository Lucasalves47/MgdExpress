package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;

public class Formulario {

    public static String formulario(String url, Gerente gerente){
        return  """
                <!DOCTYPE html>
                       <html lang="en">
                       
                       <head>
                           <meta charset="UTF-8">
                           <meta name="viewport" content="width=device-width, initial-scale=1.0">
                           <title>Formulário de Pedido</title>
                           <style>
                               body {
                                   font-family: Arial, sans-serif;
                                   background-color: #f4f4f4;
                                   margin: 0;
                                   padding: 0;
                                   display: flex;
                                   align-items: center;
                                   justify-content: center;
                                   min-height: 100vh;
                               }
                       
                               form {
                                   background-color: #fff;
                                   padding: 20px;
                                   border-radius: 8px;
                                   box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                                   width: 100%;
                                   max-width: 400px;
                               }
                       
                               label {
                                   display: block;
                                   margin-bottom: 8px;
                               }
                       
                               input,
                               select,
                               textarea {
                                   width: 100%;
                                   padding: 8px;
                                   margin-bottom: 16px;
                                   box-sizing: border-box;
                                   border: 1px solid #ccc;
                                   border-radius: 4px;
                               }
                       
                               button {
                                   background-color: #4caf50;
                                   color: #fff;
                                   padding: 10px;
                                   border: none;
                                   border-radius: 4px;
                                   cursor: pointer;
                                   width: 100%;
                               }
                       
                               .btmMapa {
                                   width: 70%;
                               }
                       
                               button:hover {
                                   background-color: #45a049;
                               }
                       
                               @media (min-width: 768px) {
                       
                                   /* Ajuste de layout para telas maiores, por exemplo, notebooks */
                                   form {
                                       width: 400px;
                                   }
                               }
                       
                               #map {
                                   width: 100%;
                                   height: 90%;
                       
                               }
                       
                               #fechar {
                                   margin-left: 80%;
                                   width: 20%;
                                   font-size: large;
                               }
                       
                               #fechar:hover {
                                   background-color: red;
                               }
                       
                               #mapa {
                                   display: none;
                                   position: absolute;
                                   width: 80%;
                                   height: 90%;
                               }
                           </style>
                       </head>
                       
                       <body>
                           <div id="mapa">
                               <button id="fechar" onclick="fecharMapa()">x</button>
                               <div id="map">
                       
                               </div>
                           </div>
                           <form id="pedidoForm">
                               <label for="nomePedido">Nome Do Pedido:</label>
                               <input type="text" id="nomePedido" name="nomePedido" required />
                       
                               <label for="localDestino">Local de Destino:</label>
                               <input type="text" id="localDestino" name="localDestino" required />
                       
                               <button type="button" onclick="mostrarMapa()" class="btmMapa">selecionar localizacao no mapa</button>
                       
                              
                               <input type="hidden" id="lng" name="lng" required />
                       
                             
                               <input type="hidden" id="lat" name="lat" required />
                       
                               <label for="valorPedido">Valor:</label>
                               <input type="number" id="valorPedido" name="valorPedido" required />
                       
                               <label for="metodoPagamento">Metodo De Pagamento:</label>
                               <select id="metodoPagamento" name="metodoPagamento">
                                   <option value="opcao1">DINHEIRO</option>
                                   <option value="opcao2">CRÉDITO</option>
                                   <option value="opcao3">DÉBITO</option>
                                   <option value="opcao4">VALE REFEIÇÃO</option>
                                   <option value="opcao5">VALE ALIMENTAÇÃO</option>
                                   <option value="opcao6">VALE-PRESENTE</option>
                                   <option value="opcao7">CARTEIRA DIGITAL</option>
                                   <option value="opcao8">PIX</option>
                                   <option value="opcao9">não especificado</option>
                               </select>
                       
                               <label for="troco">Troco:</label>
                               <input type="number" id="troco" name="troco" required />
                       
                               <label for="troco">Troco:</label>
                               <input type="number" id="troco" name="troco" required />
                       
                       
                       
                       
                               <label for="itensDoPedido">Itens do Pedido:</label>
                               <textarea id="itensDoPedido" name="itensDoPedido" placeholder="Digite seu texto aqui..." rows="8"
                                   cols="50"></textarea>
                       
                       
                               <button type="button" onclick="enviarPedido()">Criar</button>
                       
                           </form>
                                            <button style="background-color: #808080;margin: 28px;width: 90%;"   onclick="carregarPagina('"""+url+"/site/gerente/home')"+"""
                                                               ">Cancelar</button>
                       
                       </body>
                       
                       <script>
                           function mostrarMapa() {
                               var map = document.getElementById('mapa')
                               map.style.display = 'block';
                           }
                           function fecharMapa() {
                               var map = document.getElementById('mapa')
                               map.style.display = 'none';
                           }
                           function initMap() {
                               // Configurações iniciais do mapa
                               var mapOptions = {
                                   center: {
                                       lat: """+gerente.getLocalizacao().getLatitude()+"""
                                       , lng: """ +gerente.getLocalizacao().getLongitude()+ """
                                   },
                                   zoom: 10
                               };
                       
                               // Criação do mapa
                               map = new google.maps.Map(document.getElementById('map'), mapOptions); // Atribuindo o mapa à variável global
                       
                               var marker = null
                               map.addListener('click', function (event) {
                                   // Atualiza a última posição conhecida do mouse
                       
                                   document.querySelector("#pedidoForm #lng").value = event.latLng.lng()
                                   document.querySelector("#pedidoForm #lat").value = event.latLng.lat()
                       
                                   if (marker == null) {
                                       marker = new google.maps.Marker({
                                           position: event.latLng,
                                           map: map,
                                           title: "local entrega",
                                       });
                                   }
                                   else {
                                       marker.setMap(null);
                                       marker = new google.maps.Marker({
                                           position: event.latLng,
                                           map: map,
                                           title: "local entrega",
                                       });
                                   }
                       
                               });
                           }
                       </script>
                       <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async
                           defer></script>
                       
                       </html>         
                """;
    }
}
