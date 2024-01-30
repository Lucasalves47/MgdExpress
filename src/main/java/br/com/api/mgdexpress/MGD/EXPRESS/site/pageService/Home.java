package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class Home {

    public static String home(String url){
        return """
                <head>
                    <title>MGD EXPRESS</title>
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
                         
                                
                         
                                 
                    </style>
                </head>
                                
                                
                <nav>
                    <h2>MGD EXPRESS</h2>
                    <div>
                        <button onclick="clearInterval(intervalId);carregarPagina('https://mgdexpress-production.up.railway.app/site/gerente/criar')">Novo
                            Pedido</button>
                        <button onclick="clearInterval(intervalId);listarPedidos();">Meus Pedidos</button>
                       
                        <button onclick="clearInterval(intervalId);listarHistorico();">Histórico</button>
                    </div>
                </nav>
                                
                                
                <main>
                    <div id="map"></div>
                    <div id="cards"></div>
                </main>
                
                <script>
                    let cards = document.getElementById('cards')
                    var dados = [{ nome: 'loja1', local: 'av. Gov', classe: 'c1' },
                    { nome: 'loja2', local: 'av. Gov', classe: 'c2' },
                    { nome: 'loja3', local: 'av. Gov', classe: 'c3' },
                    { nome: 'loja4', local: 'av. Gov', classe: 'c4' },
                    { nome: 'loja5', local: 'av. Gov', classe: 'c5' },
                    { nome: 'loja6', local: 'av. Gov', classe: 'c6' }]
                                
                                
                    dados.forEach(function (dado) {
                        let novoElemento = document.createElement('div')
                        novoElemento.innerHTML = `<div id="card" class="${dado.classe}" onmousedown="iniciarArrastar(event, '.${dado.classe}')">
                            <h3>${dado.nome}</h3>
                            <button class="btn-detalhe" onclick="redirectToDetailsPage()">
                        <i class="fas fa-info-circle"></i>
                      </button>
                            <p>${dado.local}</p>
                           \s
                        </div>`
                                
                        cards.appendChild(novoElemento)
                    })
                                
                                
                                
                                
                                
                                
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
                    defer></script> """;
    }
}