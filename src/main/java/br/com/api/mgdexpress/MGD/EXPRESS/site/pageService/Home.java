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
                            text-align: center;
                            padding: 20px;
                        }
                                
                        #map {
                            width: 100%;
                            height: 600px;
                            /* Ajuste a altura conforme necessário */
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
                        <button onclick="clearInterval(intervalId);listarHistoricoEntregas()')">Entregas do dia</button>
                       
                        <button onclick="clearInterval(intervalId);listarHistorico();">Histórico</button>
                    </div>
                </nav>
                                
                                
                <main>
                    <div id="map"></div>
                </main>
                
                <script>
                    var markers;
                    buscarMotoboys();
                    
                    
                    function initMap(localizacoes) {
                        // Configurações iniciais do mapa
                        var mapOptions = {
                            center: { lat: -23.550520, lng: -46.633308 }, // Coordenadas iniciais
                            zoom: 10
                        };
                   
                        // Criação do mapa
                        var map = new google.maps.Map(document.getElementById('map'), mapOptions);
                   
                        // Lista de marcadores
                         markers = localizacoes;
                   
                        // Criação dos marcadores iniciais
                        markers.forEach(function(markerInfo) {
                            var marker = new google.maps.Marker({
                                position: markerInfo.position,
                                map: map,
                                title: markerInfo.title
                            });
                   
                            // Adiciona o marcador ao array para referência futura
                            markerInfo.marker = marker;
                        });
                   
                        // Função para atualizar as posições dos marcadores
                        
                        // Define o intervalo para atualizar as posições dos marcadores a cada 5 segundos (5000 milissegundos)
                        
                    }
                    
                    function updateMarkersPosition(localizacoes) {
                       
                            // Itera sobre a lista de marcadores e atualiza suas posições
                            markers.forEach(function(markerInfo) {
                               
                               
                   
                                // Atualiza a posição do marcador
                                markerInfo.marker.setPosition(markerInfo.position);
                            });
                        }
                        
                        var intervalId = setInterval(buscarMotoboys2, 10000);
                    </script>
                    
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async
                    defer></script> """;
    }
}


