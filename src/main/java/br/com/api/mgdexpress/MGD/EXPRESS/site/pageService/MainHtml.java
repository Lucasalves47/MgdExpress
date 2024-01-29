package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class MainHtml {
    public static String html(String url) {
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
                                
                                
                        var urlRoot = '"""+url+"'"+"""
                                
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
                                .then(data => initMap(data))
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
                                .then(data => {
                                    console.log(data);
                                    updateMarkersPosition(data)
                                })
                                .catch(error => console.error('Erro na requisição:', error));
                        }
                                
                                
                                
                        function listarPedidos() {
                                
                                
                            fetch(`${urlRoot}/pedidos/pendente/gerente`, {
                                method: 'GET',
                                headers: {
                                    'Authorization': `Bearer ${token}`,
                                    'Content-Type': 'application/json'
                                }
                            })
                                .then(response => response.json())
                                .then(data => {
                                
                                    data.forEach(interData => {
                                        const cardContainer = document.getElementById('cards');
                                
                                        interData.forEach(cardData => {
                                
                                
                                
                                            let novoElemento = document.createElement('div')
                                
                                            novoElemento.innerHTML = `<div id="card" class="card${cardData.id}" onmousedown="iniciarArrastar(event, '.card${cardData.id}')">
                            <h3>${cardData.nomePedido}</h3>
                            <button class="btn-detalhe" onclick="carregarPagina('${url}/site/gerente/pedido/detalhes/${cardData.id}')">
                        <i class="fas fa-info-circle"></i>
                      </button>
                            <p>${cardData.localDestino}</p>
                           \s
                        </div>`
                                            cardContainer.appendChild(novoElemento);
                                        });
                                    });
                                })
                                .catch(error => {
                                    console.error('Erro na requisição:', error);
                                });
                                
                        };
                                
                                
                                
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
                                                                           <a onclick="carregarPagina('${url}historico/detalhes/${item.id}')">
                                                                               <button>Detalhes</button>
                                                                           </a>`;
                                        historicoList.appendChild(li);
                                    });
                                })
                                .catch(error => {
                                    console.error('Erro:', error);
                                });
                        };
                                
                        function listarHistoricoEntregas() {
                                
                                
                            carregarPagina(`${url}historicoEntregas`);
                                
                            fetch(`${urlRoot}/gerente/listarMotoboysEntregas`, {
                                method: 'GET',
                                headers: {
                                    'Authorization': `Bearer ${token}`,
                                    'Content-Type': 'application/json'
                                }
                            })
                                .then(response => response.json())
                                .then(data => {
                                    console.log(data);
                                    data.forEach(item => {
                                        const historicoList = document.getElementById('container');
                                
                                        const li = document.createElement('li');
                                        li.innerHTML = `<h3>${item.nomeMotoboy}</h3>
                                                                                   <p>Valor: R$ ${item.valor}</p>
                                                                                    <p>Entregas: ${item.entregas}</p>
                                                                                   <p>KM: ${item.km}</p>
                                                                        \s
                                                                             \s
                                                                               <button class="btn-detalhe" onclick="carregarPagina('${url}historico/detalhes/${item.id}')">
                                                                                     <i class="fas fa-info-circle"></i>
                                                                                   </button>
                                                                       \s
                                                                                   `;
                                        historicoList.appendChild(li);
                                    });
                                })
                                .catch(error => {
                                    console.error('Erro:', error);
                                });
                        };
                                
                                
                                
                                
                    </script>
                </body>
                                
                </html>
                """;
    }
}
