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
                               
                        #cardsTela {
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
                        
                         .btnpageable{
                            background-color: #4CAF50;
                            color: white;
                            padding: 10px 20px;
                            border: none;
                            border-radius: 5px;
                            cursor: pointer;
                            font-size: 16px;
                            position: absolute;
                            bottom: 9px;
                        }
                               
                        .btndetalhe {
                            background-color: #d5dcd500;
                            color: cadetblue;
                            border: none;
                            padding: 10px 15px;
                            border-radius: 100%;
                            cursor: pointer;
                            margin-left: 50%;
                            display: inline;
                        }
                       
                        
                         .numero_de_tela{
                        
                             color: white;
                             font-size: 16px;
                             position: absolute;
                             bottom: 9px;
                             margin-left: 30%;
                             }
                        
                        .btnpageableleft{
                                background-color: #4CAF50;
                            color: white;
                            padding: 10px 20px;
                            border: none;
                            border-radius: 5px;
                            cursor: pointer;
                            font-size: 16px;
                            position: absolute;
                            bottom: 9px;
                              margin-left: 83%;
                        } 
                        .loaderIcon {
                                border: 4px solid #f3f3f3;
                                border-top: 4px solid #3498db;
                                border-radius: 50%;
                                width: 30px;
                                height: 30px;
                                animation: spin 1s linear infinite;
                                position: absolute;
                                left: 50%;
                                top: 50%;
                                transform: translate(-50%, -50%);
                                display: none;
                            }
                        
                            @keyframes spin {
                                0% { transform: rotate(0deg); }
                                100% { transform: rotate(360deg); }
                            }
                    </style>
                </head>
                               
                <nav>
                    <h2>MGD EXPRESS</h2>
                    <div>
                        <button onclick="mostrarEEsconderPedidos()">Pedidos</button>
                        <button onclick="clearInterval(intervalId);clearInterval(getPedidoIntereval);carregarPagina('"""+url+"/site/gerente/criar'"+"""
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
                    
                    <div id="cardsTela">
                        <div id="cards"> </div>
                         <div class="loaderIcon"></div>
                        <button class="btnpageable" onclick="back()"><</button>
                        <p class="numero_de_tela"></p>
                        <button class="btnpageableleft" onclick="next()">></button>
                       
                    </div>
                </main>
                               
                <script>
                    var page = 0
                    var elemento = document.querySelector(".numero_de_tela");
                    
               
                    var pageMax;
                    listarPedidos(page)
                         
                               
                    var map; // Variável global para o mapa
                    var lastMousePosition;
                    var mouseStopTimer;
                    
                    function back() {
                    if((page-1) >= 0){
                        page -=1
                        document.getElementById('cards').style.display = 'none'
                        showLoaderIcon()
                        listarPedidos(page)
                        elemento.textContent = `página ${page+1} de ${pageMax}`;
                        }
                       
                    }
                    function next() {
                        if((page+1) < pageMax){
                            page +=1
                            document.getElementById('cards').style.display = 'none'
                            showLoaderIcon()
                            listarPedidos(page)
                            elemento.textContent = `página ${page+1} de ${pageMax}`;
                           
                        }
                    }
                    
                    function showLoaderIcon() {
                                document.querySelector(".loaderIcon").style.display = "block";
                            }
                    
                            // Função para ocultar o ícone de carregamento
                    function hideLoaderIcon() {
                           document.querySelector(".loaderIcon").style.display = "none";
                       }
                               
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
                        let cards = document.getElementById("cardsTela")
                               
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
                        
                        function desenharTodasLocalizacaoEntrega(latitude,longitude,nomePedido,tamanho,url){
                             var marker = new google.maps.Marker({
                                            position: { lat:parseFloat(latitude) , lng:parseFloat(longitude)},
                                            map: map,
                                            title: nomePedido+"",
                                            icon: {
                                                    url: url,
                                                    scaledSize: new google.maps.Size(tamanho, tamanho) // Tamanho do ícone personalizado
                                                }
                                          
                                        });
                        }
                               
                               
                    function passaPagelistarPedidos() {
                        listarPedidos(page)
                    }          
                               
                    var intervalId = setInterval(buscarMotoboys2, 10000);
                    var getPedidoIntereval = setInterval(passaPagelistarPedidos, 27000);
                </script>
                               
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async
                    defer></script>""";
    }
}