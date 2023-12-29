package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class MainHtml {
    public static String html() {
        return """
                <!DOCTYPE html>
                       <html lang="pt-br">
                       
                       <head>
                           <meta charset="UTF-8">
                           <meta name="viewport" content="width=device-width, initial-scale=1.0">
                           <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
                       
                       
                       </head>
                       
                       <body>
                       
                       <div id="content-container">
                           <!-- O conteúdo carregado será exibido aqui -->
                       </div>
                       
                       <script>
                       
                           var url = "https://mgdexpressapi-production.up.railway.app/site/gerente/";
                           var token;
                       
                           if (token == null) {
                               fetch('https://mgdexpressapi-production.up.railway.app/login', {
                                   method: 'GET',
                                   headers: {
                                       'Content-Type': 'application/json'
                                   }
                               })
                                   .then(response => response.json())
                                   .then(data => {
                                       $('#content-container').html(data.page);
                       
                                   })
                                   .catch(error => console.log(error));
                           }
                       
                           function login() {
                               // Obter os valores dos campos de entrada
                               var username = document.getElementById("username").value;
                               var password = document.getElementById("password").value;
                       
                               // Criar um objeto com os dados do formulário
                               var formData = {
                                   username: username,
                                   password: password
                               };
                       
                               // Converter o objeto em uma string JSON
                               var jsonData = JSON.stringify(formData);
                       
                               // Enviar os dados para a URL usando AJAX
                               $.ajax({
                                   type: "POST",
                                   url: "https://mgdexpressapi-production.up.railway.app/login",
                                   data: jsonData,
                                   contentType: "application/json",
                                   success: function (response) {
                                       token = response
                                       console.log(response);
                                       carregarPagina(`${url}home`)
                       
                                       // Lógica adicional para lidar com a resposta do servidor
                                   },
                                   error: function (error) {
                                       console.error("Erro na requisição AJAX:", error);
                                       // Lógica adicional para lidar com erros
                                   }
                               });
                           }
                       
                           function carregarPagina(url) {
                           console.log(url)
                               fetch(url, {
                                   method: 'GET',
                                   headers: {
                                       'Authorization': `Bearer ${token}`,
                                       'Content-Type': 'application/json'
                                   }
                               })
                                   .then(response => response.json())
                                   .then(data => {
                                
                                       $('#content-container').html(data.page);
                                   })
                                   .catch(error => console.error('Erro:', error));
                           }
                       
                       
                           function enviarPedido() {
                               var formData = new FormData(document.getElementById('pedidoForm'));
                               var urlpedido = `https://mgdexpressapi-production.up.railway.app/pedidos`;
                       
                               fetch(urlpedido, {
                                   method: 'POST',
                                   headers: {
                                       'Authorization': `Bearer ${token}`,
                                       'Content-Type': 'application/json'
                                   },
                                   body: JSON.stringify(Object.fromEntries(formData))
                               })
                                   .then(response => carregarPagina(`${url}sucesso`))
                       
                                   .catch(error => console.error('Erro:', error));
                           }
                       
                           function buscarMotoboys() {
                                    fetch('https://mgdexpressapi-production.up.railway.app/motoboy/EmEntregas/gerente', {
                                        method: 'GET',
                                        headers: {
                                            'Authorization': `Bearer ${token}`,
                                            'Content-Type': 'application/json'
                                        }
                                    })
                                        .then(response => response.json())
                                        .then(data =>initMap(data))
                                        .catch(error => console.error('Erro na requisição:', error));
                                }
                                
                           function buscarMotoboys2() {
                                    fetch('https://mgdexpressapi-production.up.railway.app/motoboy/EmEntregas/gerente', {
                                        method: 'GET',
                                        headers: {
                                            'Authorization': `Bearer ${token}`,
                                            'Content-Type': 'application/json'
                                        }
                                    })
                                        .then(response => response.json())
                                        .then(data =>updateMarkersPosition(data))
                                        .catch(error => console.error('Erro na requisição:', error));
                                }
                       
                       
                       
                           function listarPedidos() {
                               carregarPagina(`${url}meusPedidos`)
                       
                               
                               fetch(`https://mgdexpressapi-production.up.railway.app/pedidos/pendente/gerente`, {
                                   method: 'GET',
                                   headers: {
                                       'Authorization': `Bearer ${token}`,
                                       'Content-Type': 'application/json'
                                   }
                               })
                                   .then(response => response.json())
                                   .then(data => {
                                       console.log("Dados recebidos:");
                                       console.log(data);
                                       
                                       const cardContainer = document.getElementById('card-container');
                              
                               
                                       data.forEach(interData => {
                                            interData.forEach(cardData =>{
                                       
                                           console.log("Dados do card:");
                                           console.log(cardData.localDestino);
                               
                                           const card = document.createElement('div');
                                           card.className = 'card';
                               
                                           const cardContent = document.createElement('div');
                                           cardContent.className = 'card-content';
                               
                                           const cardDetails = `
                                               <p class="titulo"><strong>cardData.nomePedido</strong></p>
                                               <p><strong>Valor:</strong>cardData.valor</p>
                                               <p><strong>Local de Destino:</strong>cardData.localDestino</p>
                                               <a onclick="carregarPagina('urlsite/gerente/detalhes/cardData.id')"><button>Detalhes</button></a>
                                           `;
                               
                                           cardContent.innerHTML = cardDetails;
                               
                                           card.appendChild(cardContent);
                                           cardContainer.appendChild(card);
                                      }); });
                                   })
                                   .catch(error => {
                                       console.error('Erro na requisição:', error);
                                   });
                               
                           };
                       
                           //area destinada ou codigo do listar pedidos em andamento
                           // fim da area
                       
                           function listarHistorico() {
                       
                               carregarPagina(`${url}historico`)
                       
                               fetch(`https://mgdexpressapi-production.up.railway.app/historico/gerente`,{
                                   method: 'GET',
                                   headers: {
                                       'Authorization': `Bearer ${token}`,
                                       'Content-Type': 'application/json'
                                   }
                               })
                                   .then(response => response.json())
                                   .then(data => {
                       
                                       data.forEach(item => {
                                           console.log(item.id);
                       
                                           const li = document.createElement('li');
                                           li.innerHTML = `
                       
                               <p>Data de Entrega: ${item.dataEntrega}</p>
                               <p>Motoboy: ${item.motoboyNome}</p>
                               <p>Estabelecimento: ${item.nomeStabelecimento}</p>
                               <p>Valor: R$ ${item.valor.toFixed(2)}</p>
                               <a onclick="carregarPagina('${url}site/gerente/historico/detalhes/${item.id}'"><button>Detalhes</button></a>
                           `;
                                           historicoList.appendChild(li);
                                       });
                                   })
                                   .catch(error => {
                                       // trate erros de requisição
                                   });
                       
                               const historicoList = document.getElementById('historico-list');
                           };
                       
                       </script>
                       </body>
                       
                       </html>
                """;
    }
}
