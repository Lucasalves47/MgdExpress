package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class Home {

    public static String home(String url){
        return """
                <head>
                      <title>MGD EXPRESS</title>
                      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
                  
                      <style>
                          body {
                              margin: 0;
                              padding: 0;
                              font-family: Arial, sans-serif;
                          }
                  
                          nav {
                              background-color: #333;
                              color: white;
                              padding: 10px;
                              display: flex;
                              justify-content: space-between;
                              align-items: center;
                          }
                  
                          nav h2 {
                              margin: 0;
                          }
                  
                          nav a {
                              color: white;
                              text-decoration: none;
                              margin-left: 15px;
                          }
                  
                          main {
                              margin-top: 0%;
                          }
                  
                          #map {
                              width: 100%;
                              height: 92%;
                          }
                  
                          nav button {
                              background-color: #4CAF50;
                              color: white;
                              padding: 10px 20px;
                              border: none;
                              border-radius: 5px;
                              cursor: pointer;
                              font-size: 16px;
                              margin-left: 15px;
                          }
                  
                          nav button:hover {
                              background-color: #45a049;
                          }
                  
                          #cards {
                              position: absolute;
                              top: 0;
                              left: 0;
                              width: 20%;
                              height: 95%;
                              background-color: #333333cc;
                              margin-top: 5vh;
                             
                          }
                  
                          #card {
                              margin: 5%;
                              margin-top: 8%;
                              background-color: #232323;
                              color: white;
                              border-radius: 20px;
                              cursor: pointer;
                              padding: 5px;
                          }
                  
                          #card p {
                              margin: 15px;
                              margin-top: 0%;
                              margin-bottom: 5px;
                              font-size: 12px;
                          }
                  
                          #card h3 {
                              margin: 15px;
                              margin-bottom: 0%;
                              font-size: 15px;
                              margin-top: 8px;
                              display: inline;
                          }
                  
                          .btn-detalhe {
                              background-color: #d5dcd500;
                              color: cadetblue;
                              border: none;
                              padding: 10px 15px;
                              border-radius: 100%;
                              cursor: pointer;
                              margin-left: 50%;
                              display: inline;
                          }
                      </style>
                  </head>
                  
                  <nav>
                      <h2>MGD EXPRESS</h2>
                      <div>
                          <button onclick="mostrarEEsconderPedidos()">Pedidos</button>
                          <button onclick="listarHistoricoEntrega();">Motoboy</button>
                          <button onclick="carregarPagina('"""+url+"/site/gerente/criar')"+"""
                        ">Novo Pedido</button>
                        <button onclick="listarHistoricoEntregas();">Entregas do dia</button>
                        <button onclick="listarHistorico();">Histórico</button>
                      </div>
                  </nav>
                  
                  <main>
                      <div id="map"></div>
                      <div id="cards"></div>
                  </main>
                  
                  
                <script>
                                       var markers = [];
                                   
                                       function initMap(localizacoes) {
                                           // Configurações iniciais do mapa
                                           var mapOptions = {
                                               center: { lat: -23.550520, lng: -46.633308 }, // Coordenadas iniciais
                                               zoom: 18
                                           };
                                   
                                           // Criação do mapa
                                           map = new google.maps.Map(document.getElementById('map'), mapOptions); // Atribuindo o mapa à variável global
                                   
                                   
                                   
                                           marker = new google.maps.Marker({
                                               position: { lat: -23.550520, lng: -46.633308 },
                                               map: map,
                                               title: 'Marcador1',
                                           });
                                   
                                           marker2 = new google.maps.Marker({
                                               position: { lat: -23.554520, lng: -46.636308 },
                                               map: map,
                                               title: 'Marcador2',
                                           });
                                   
                                           marker3 = new google.maps.Marker({
                                               position: { lat: -23.559520, lng: -46.638308 },
                                               map: map,
                                               title: 'Marcador3',
                                           });
                                   
                                           markers.push(marker)
                                           markers.push(marker2)
                                           markers.push(marker3)
                                   
                                           google.maps.event.addListenerOnce(map, 'idle', function () {
                                               var pixelPosition = getMarkerPositionInPixels(marker);
                                   
                                           });
                                   
                                           map.addListener('mousemove', function (event) {
                                               // Atualiza a última posição conhecida do mouse
                                               lastMousePosition = { lat: event.latLng.lat(), lng: event.latLng.lng() };
                                   
                                           });
                                       }
                                   
                                       function mostrarEEsconderPedidos() {
                                           let cards = document.getElementById("cards")
                                   
                                           if (cards.style.display !== 'none') {
                                               cards.style.display = 'none';
                                           }
                                           else {
                                               cards.style.display = 'block';
                                           }
                                       }
                                   </script>
                                   
                                   <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async
                                       defer></script>""";
    }
}


