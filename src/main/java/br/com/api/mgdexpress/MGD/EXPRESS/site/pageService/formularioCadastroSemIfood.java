package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class formularioCadastroSemIfood {

    public static String html(String url){
        return """
                <!DOCTYPE html>
                <html lang="pt-br">
                                
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Formulário de Registro</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            min-height: 100vh;
                            margin: 0;
                            background-color: #f4f4f4;
                        }
                                
                        #registro-form {
                            display: block;
                            width: 100%;
                            max-width: 400px;
                            padding: 20px;
                            border: 1px solid #ccc;
                            border-radius: 5px;
                            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                            background-color: #fff;
                        }
                                
                        #registro-form h2 {
                            text-align: center;
                        }
                                
                        #registro-form label {
                            display: block;
                            margin-bottom: 8px;
                        }
                                
                        #registro-form input {
                            width: 100%;
                            padding: 8px;
                            margin-bottom: 15px;
                            box-sizing: border-box;
                            border: 1px solid #ccc;
                            border-radius: 4px;
                        }
                                
                        button {
                            background-color: #4caf50;
                            color: white;
                            padding: 10px 15px;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            width: 100%;
                        }
                                
                        .ifoodfildset {
                            margin-bottom: 15px;
                        }
                                
                        .popUp {
                            width: 100%;
                            max-width: 400px;
                            height: 300px;
                            position: absolute;
                            top: auto;
                            left: auto;
                            right: auto;
                            bottom: auto;
                                
                            padding: 20px;
                            border: 1px solid #ccc;
                            border-radius: 5px;
                            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                            background-color: #fff;
                        }
                                
                        .link {
                            color: rgb(26, 94, 171);
                            text-decoration: none;
                        }
                                
                        .link:hover {
                            text-decoration: underline;
                            cursor: pointer;
                        }
                                
                        .code {
                            size: 30px;
                            margin-left: 36%;
                        }
                                
                        #btnPopUp {
                            margin-top: 25px;
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
                                  top: auto;
                            left: auto;
                            right: 30pxs;
                            bottom: auto;
                                   width: 80%;
                                   height: 90%;
                               }
                    </style>
                </head>
                                
                <body>
                    <div id="registro-form">
                        <h2>Registro</h2>
                        <div id="mapa">
                               <button id="fechar" onclick="fecharMapa()">x</button>
                               <div id="map">
                       
                               </div>
                           </div>
                        <form id="registroForm">
                        
                        
                                
                                
                            <label for="nome">name:</label>
                            <input type="text" id="nome" name="nome" required>
                                
                            <label for="nomeEstabelecimento">Estabelecimento</label>
                            <input type="text" id="nomeEstabelecimento" name="nomeEstabelecimento" required>
                                
                            <label for="telefone">Telefone:</label>
                            <input type="text" id="telefone" name="telefone" required>
                                
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" required>
                                
                            <label for="senha">Senha:</label>
                            <input type="password" id="senha" name="senha" required>
                                
                            <button type="button" onclick="mostrarMapa()" class="btmMapa">selecionar localizacao no mapa</button>
                            <input type="hidden" id="lng" name="lng" required />
                            <input type="hidden" id="lat" name="lat" required />
                                
                            <label for="localEstabelecimento">Local Estabelecimento</label>
                            <input type="text" id="localEstabelecimento" name="localEstabelecimento" required>
                                
                                
                            <label for="cnpj">CNPJ:</label>
                            <input type="text" id="cnpj" name="cnpj" required>
                                
                            <label for="merchatId">Id Ifood</label>
                            <input type="text" id="merchatId" name="merchatId" required placeholder="id da loja no ifood">
                                
                                
                                
                                
                            <button type="button" onclick="enviarFormulario()">Registrar</button>
                        </form>
                    </div>
                                
                                
                                
                    <script>
                          function enviarFormulario() {
                              var formulario = document.getElementById('registroForm');
                              var formData = new FormData(formulario);
                 
                              fetch('"""+url+"/gerente-temporario/cadastroSemIfood', { "+"""
                                                                  method: 'POST',
                                  body: JSON.stringify(Object.fromEntries(formData)),
                                  headers: {
                                      'Content-Type': 'application/json'
                                  }
                              })
                                  .then(response => {
                                      if (!response.ok) {
                                          alert("Usuario não encontrado no Ifood  verifique o nome e o nome do estabelecimento");
                                      } else {
                                          window.location.href = '"""+url+"/site/gerente/cadastro/pendente'"+"""
                                      }
                                  })
                          }
                                
                                
                                
                    </script>
                                
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
                                     lat: -19.9167
                                     , lng: -43.9333
                                 },
                                 zoom: 10
                             };
                                
                            // Criação do mapa
                            map = new google.maps.Map(document.getElementById('map'), mapOptions); // Atribuindo o mapa à variável global
                                
                            var marker = null
                            map.addListener('click', function (event) {
                                // Atualiza a última posição conhecida do mouse
                                
                                var evento = event.latLng.lng()
                                console.log(evento)
                                document.querySelector("#registroForm #lng").value = evento
                                document.querySelector("#registroForm #lat").value = event.latLng.lat()
                                
                                if (marker == null) {
                                    marker = new google.maps.Marker({
                                        position: event.latLng,
                                        map: map,
                                        title: "Loja",
                                    });
                                }
                                else {
                                    marker.setMap(null);
                                    marker = new google.maps.Marker({
                                        position: event.latLng,
                                        map: map,
                                        title: "Loja",
                                    });
                                }
                                
                            });
                        }
                    </script>
                    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap"
                        async defer></script>
                                
                </body>
                                
                </html>""";
    }
}
