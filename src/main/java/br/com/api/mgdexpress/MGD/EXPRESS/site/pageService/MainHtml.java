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
                               
                                  
                                   var urlRoot = "https://mgdexpress-production.up.railway.app"
                                   var url = `${urlRoot}/site/gerente/`;
                                   var token;
                               
                                   if (token == null) {
                                      
                                       fetch(`${urlRoot}/login`, {
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
                                           url: `${urlRoot}/login`,
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
                                       var urlpedido = `${urlRoot}/pedidos`;
                               
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
                                            fetch(`${urlRoot}/motoboy/EmEntregas/gerente`, {
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
                                            fetch(`${urlRoot}/motoboy/EmEntregas/gerente`, {
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
                               
                                      
                                       fetch(`${urlRoot}/pedidos/pendente/gerente`, {
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
                                      
                                               data.forEach(interData => {
                                                    interData.forEach(cardData =>{
                                                    const cardContainer = document.getElementById('card-container');
                                      
                                              
                                                   console.log("Dados do card:");
                                                   console.log(cardData.localDestino);
                                      
                                                   const card = document.createElement('div');
                                                   card.className = 'card';
                                      
                                                   const cardContent = document.createElement('div');
                                                   cardContent.className = 'card-content';
                                      
                                                   const cardDetails = `
                                                 
                                                       <p class="titulo"><strong>${cardData.nomePedido}</strong></p>
                                                       <p><strong>Valor:</strong> ${cardData.valor}</p>
                                                       <p><strong>Local de Destino:</strong> ${cardData.localDestino}</p>
                                                       <a onclick="carregarPagina('${url}detalhes/${cardData.id}')"><button>Detalhes</button></a>
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
                                            
                                              
                                               carregarPagina(`${url}historico`);
                                   
                                               fetch(`${urlRoot}/historico/gerente`, {
                                                   method: 'GET',
                                                   headers: {
                                                       'Authorization': `Bearer ${token}`,
                                                       'Content-Type': 'application/json'
                                                   }
                                               })
                                               .then(response => response.json())
                                               .then(data => {
                                                   data.forEach(item => {
                                                       const historicoList = document.getElementById('historico-list');
                                   
                                                       const li = document.createElement('li');
                                                       li.innerHTML = `
                                                           <p>Data de Entrega: ${item.dataEntrega}</p>
                                                           <p>Motoboy: ${item.motoboyNome}</p>
                                                           <p>Estabelecimento: ${item.nomeStabelecimento}</p>
                                                           <p>Valor: R$ ${item.valor.toFixed(2)}</p>
                                                           <a onclick="preencherDetalhesHistorico('${urlRoot}/historico/pedido/${item.id}')">
                                                               <button>Detalhes</button>
                                                           </a>`;
                                                       historicoList.appendChild(li);
                                                   });
                                               })
                                               .catch(error => {
                                                   console.error('Erro:', error);
                                               });
                                           };
                                           
                                           
                                           function preencherDetalhesHistorico(urll) {
                                           
                                                        fetch(urll, {
                                           method: 'GET',
                                           headers: {
                                               'Authorization': `Bearer ${token}`,
                                               'Content-Type': 'application/json'
                                           }
                                       })
                                           .then(response => response.json())
                                           .then(detalhes => {
                                       
                                               const historicoDetails = document.getElementById('historico-details');
                                                       historicoDetails.innerHTML = `
                                                           <h3>Histórico</h3>
                                                           <p>Nome do Estabelecimento: ${detalhes.nomeEstabelecimento}</p>
                                                           <p>Local de Origem: ${detalhes.localOrigem}  Local de Destino: ${detalhes.localDestino}</p>
                                                           <p>Valor: ${detalhes.valor}</p>
                                                           <p>Observação: ${detalhes.observacao}</p>
                                                           <p>Itens do Pedido: ${detalhes.itensPedido}</p>
                                                           <p>Data de Criação: ${detalhes.dataCriacao}   Data de Entrega: ${detalhes.dataEntrega}</p>
                                                           <p>Motoboy:</p>
                                                           <ul>
                                                               <li>Nome: ${detalhes.motoboy.nome}</li>
                                                               <li>Telefone: ${detalhes.motoboy.telefone}</li>
                                                               <li>Email: ${detalhes.motoboy.email}</li>
                                                           </ul>
                                                           <p>Gerente:</p>
                                                           <ul>
                                                               <li>Nome: ${detalhes.gerente.nome}</li>
                                                               <li>Telefone: ${detalhes.gerente.telefone}</li>
                                                               <li>Email: ${detalhes.gerente.email}</li>
                                                           </ul>`;
                                           })
                                           .catch(error => console.error('Erro:', error));
                                   
                               </script>
                               </body>
                               
                               </html>
                """;
    }
}
