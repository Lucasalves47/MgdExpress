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
                            display: none;
                               
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
                        <button onclick="clearInterval(intervalId);carregarPagina('""\"+url+"/site/gerente/criar'"+""\"
                )">Novo Pedido</button>
                       
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
                                        console.log('card', classe.replace(".card", ""))
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
                    var customIcon;
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
                               
                        customIcon = {
                            url:'data:image/png;base64,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',
                            scaledSize: new google.maps.Size(50, 50), // Tamanho do ícone
                            origin: new google.maps.Point(0, 0), // Origem do ícone (0,0 é o canto superior esquerdo)
                            anchor: new google.maps.Point(25, 50) // Ponto de ancoragem do ícone (onde ele será colocado no mapa)
                        };
                               
                        loc.forEach(function (markerInfo) {
                            if (markerInfo.localizacao != null) {
                                if (markerInfo.localizacao.longitude != null) {
                                    var marker = new google.maps.Marker({
                                        position: { lat: parseFloat(markerInfo.localizacao.latitude), lng: parseFloat(markerInfo.localizacao.longitude) },
                                        map: map,
                                        title: markerInfo.nome,
                                        id: markerInfo.id,
                                        icon: customIcon
                                    });
                               
                                    // Adiciona o marcador ao array para referência futura
                                    markerInfo.marker = marker;
                                    markers.push(marker)
                                   
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
                        markers.forEach(function (markerInfo) {
                            markerInfo.setMap(null);
                        });
                        loc = localizacoes
                        markers = []
                                
                        loc.forEach(function (markerInfo) {
                            if (markerInfo.localizacao != null) {
                                if (markerInfo.localizacao.longitude != null) {
                                    var marker = new google.maps.Marker({
                                        position: { lat: parseFloat(markerInfo.localizacao.latitude), lng: parseFloat(markerInfo.localizacao.longitude) },
                                        map: map,
                                        title: markerInfo.nome,
                                        id: markerInfo.id,
                                        icon: customIcon
                                    });
                               
                                    // Adiciona o marcador ao array para referência futura
                                    markerInfo.marker = marker;
                                    markers.push(marker)
                                   
                                }
                            }
                        });
                    }
                                
                   
                               
                               
                    var intervalId = setInterval(buscarMotoboys2, 10000);
                </script>
                               
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async
                    defer></script>""";
    }
}