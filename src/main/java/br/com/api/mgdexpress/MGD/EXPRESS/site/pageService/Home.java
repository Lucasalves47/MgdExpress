package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;

public class Home {

    public static String home(String url, Gerente gerente){
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
                        <button onclick="clearInterval(intervalId);clearInterval(getPedidoIntereval);carregarPagina('""\"+url+"/site/gerente/criar'"+""\"
                )">Novo Pedido</button>
                               
                        <button
                            onclick="clearInterval(intervalId);clearInterval(getPedidoIntereval);listarHistoricoEntregas();">Entregas do
                            dia</button>
                        <button
                            onclick="clearInterval(intervalId);clearInterval(getPedidoIntereval);listarHistorico();">Histórico</button>
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
                    var localEntrega;
                    buscarMotoboys();
                               
                    function initMap(localizacoes) {
                        // Configurações iniciais do mapa
                        var mapOptions = {
                            center: { lat: """+gerente.getLocalizacao().getLatitude()+"""
                                , lng:""" +gerente.getLocalizacao().getLongitude()+ """
                            },
                            zoom: 18
                        };
                               
                        // Criação do mapa
                        map = new google.maps.Map(document.getElementById('map'), mapOptions); // Atribuindo o mapa à variável global
                               
                        var lojaIcon = {
                            url: 'data:image/png;base64,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',
                            scaledSize: new google.maps.Size(100, 100), // Tamanho do ícone
                            origin: new google.maps.Point(0, 0), // Origem do ícone (0,0 é o canto superior esquerdo)
                            anchor: new google.maps.Point(25, 50) // Ponto de ancoragem do ícone (onde ele será colocado no mapa)
                        };
                               
                        var loja = new google.maps.Marker({
                            position: { lat: """+gerente.getLocalizacao().getLatitude()+"""
                            , lng:""" +gerente.getLocalizacao().getLongitude()+ """
                            },
                            map: map,
                            title:'"""+gerente.getNomeEstebelecimento()+"""
                            ',
                            
                            icon: lojaIcon
                        });
                               
                        loc = localizacoes
                               
                        customIcon = {
                            url: 'data:image/png;base64,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',
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
                    
                     function desenharLocalizacaoEntrega(latitude,longitude,nomePedido){
                            if(localEntrega != null){
                                localEntrega.setMap(null)
                                }
                            localEntrega = new google.maps.Marker({
                                            position: { lat:latitude , lng:longitude},
                                            map: map,
                                            title: nomePedido+"",
                                          
                                        });
                        }
                               
                               
                               
                               
                    var intervalId = setInterval(buscarMotoboys2, 10000);
                    var getPedidoIntereval = setInterval(listarPedidos, 27000);
                </script>
                               
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async
                    defer></script>""";
    }
}