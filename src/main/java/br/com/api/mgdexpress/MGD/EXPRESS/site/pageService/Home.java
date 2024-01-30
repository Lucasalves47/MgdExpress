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
                           \s
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

                                        console.log('marcador', marker)
                                        console.log('card', card)
                                        joinPedidoMotoboy(classe.replace(".c",""),marker.id)
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
                            center: { lat: -23.550520, lng: -46.633308 }, // Coordenadas iniciais
                            zoom: 18
                        };

                        // Criação do mapa
                        map = new google.maps.Map(document.getElementById('map'), mapOptions); // Atribuindo o mapa à variável global

                        loc = localizacoes

                    loc.forEach(function(markerInfo) {
                           var marker = new google.maps.Marker({
                               position: markerInfo.position,
                               map: map,
                               title: markerInfo.title
                               id: markerInfo.id
                           });
                   
                           // Adiciona o marcador ao array para referência futura
                           markerInfo.marker = marker;
                           markers.push(marker)
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
                </script>

                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async
                    defer></script> """;
    }
}