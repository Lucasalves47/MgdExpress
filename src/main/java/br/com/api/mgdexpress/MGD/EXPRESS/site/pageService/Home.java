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
                             height: 700px;
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
                         <button onclick="clearInterval(intervalId);carregarPagina('+url+/site/gerente/criar')">Novo Pedido</button>
                         <button onclick="clearInterval(intervalId);listarPedidos();">Meus Pedidos</button>
                         <button onclick="clearInterval(intervalId);listarHistoricoEntregas();">Entregas do dia</button>
                         <button onclick="clearInterval(intervalId);listarHistorico();">Histórico</button>
                     </div>
                 </nav>
                 
                 <main>
                     <div id="map"></div>
                     <div id="cards"></div>
                 </main>
                 
                 <script>
                     listarPedidos()
                 
                     var map; // Variável global para o mapa
                     var lastMousePosition;
                     var mouseStopTimer;
                 
                 
                     function iniciarArrastar(event, classe) {
                         let card = event.target.closest(classe);
                 
                         let offsetX = event.clientX - card.getBoundingClientRect().left;
                         let offsetY = event.clientY - card.getBoundingClientRect().top + 35;
                 
                         function arrastarElemento(event) {
                             event.preventDefault();
                             card.style.position = 'absolute';
                             card.style.left = (event.clientX - offsetX) + 'px';
                             card.style.top = (event.clientY - offsetY) + 'px';
                         }
                 
                         function pararArrastar(card, event) {
                             window.removeEventListener('mousemove', arrastarElemento);
                             window.removeEventListener('mouseup', pararArrastar);
                 
                 
                 
                             markers.forEach(function (marker) {
 var markerPosition = getMarkerPositionInPixels(marker);
                 
 if (card.style.display !== 'none') {
     if (lastMousePosition.lat <= markerPosition.lat + 0.0001 && lastMousePosition.lat >= markerPosition.lat - 0.0001 &&
         lastMousePosition.lng <= markerPosition.lng + 0.0001 && lastMousePosition.lng >= markerPosition.lng - 0.0001) {
         card.style.display = 'none';
                 
         console.log('marcador', marker.id)
         console.log('card', classe.replace(".card", "") )
         joinPedidoMotoboy(classe.replace(".card", ""), marker.id)
     }
 }
                             })
                 
                 
                 
                 
                         }
                 
                         window.addEventListener('mousemove', arrastarElemento);
                         window.addEventListener('mouseup', function (event) {
                             pararArrastar(card, event);
                         });
                     }
                 
                     function getMarkerPositionInPixels(marker) {
                 
                         return { lat: marker.position.lat(), lng: marker.position.lng() };
                     }
                 
                 
                 </script>
                 
                 <script>
                     var markers = [];
                 
                     var loc;
                     buscarMotoboys();
                 
                     function initMap(localizacoes) {
                         // Configurações iniciais do mapa
                         var mapOptions = {
                             center: { lat: -20.0544774, lng: -44.2789723 }, // Coordenadas iniciais
                             zoom: 18
                         };
                 
                         // Criação do mapa
                         map = new google.maps.Map(document.getElementById('map'), mapOptions); // Atribuindo o mapa à variável global
                 
                         loc = localizacoes
                 
                         loc.forEach(function (markerInfo) {
                             if (markerInfo.localizacao != null) {
 if (markerInfo.localizacao.longitude != null) {
     var marker = new google.maps.Marker({
         position: { lat:parseFloat(markerInfo.localizacao.latitude), lng:parseFloat(markerInfo.localizacao.longitude) },
         map: map,
         title: markerInfo.nome,
         id: markerInfo.id,
         icon:'data:image/png;base64,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
'
     });
                 
     // Adiciona o marcador ao array para referência futura
     markerInfo.marker = marker;
     markers.push(marker)
     markerInfo.localizacao.longitud
 }
                             }
                         });
                 
                 
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
                 
                     function updateMarkersPosition(localizacoes) {
                 
                         // Itera sobre a lista de marcadores e atualiza suas posições
                         loc.forEach(function (markerInfo) {
                             markerInfo.marker.setPosition({ lat:parseFloat(markerInfo.localizacao.latitude), lng:parseFloat(markerInfo.localizacao.longitude )});
                         });
                     }
                     
                 
                     var intervalId = setInterval(buscarMotoboys2, 10000);
                 </script>
                 
                 <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async
                     defer></script>""";
    }
}