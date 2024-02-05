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
                                    fetch(`${urlRoot}/motoboy/EmEntregas&Disponivel`, {
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
                                    fetch(`${urlRoot}/motoboy/EmEntregas&Disponivel`, {
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
                        
                        
                        
                                function listarPedidos(page) {
                        
                                    fetch(`${urlRoot}/pedidos/pendente/gerente?page=${page}`, {
                                        method: 'GET',
                                        headers: {
                                            'Authorization': `Bearer ${token}`,
                                            'Content-Type': 'application/json'
                                        }
                                    })
                                        .then(response => response.json())
                                        .then(data => {
                        
                                            document.getElementById("cards").innerHTML = "";
                        
                                            const cardContainer = document.getElementById('cards');
                                            pageMax = data.totalPages
                        
                                            var cor
                                            data.content.forEach(cardData => {
                                               
                                                if(cardData.status == "INICIAR" && cardData.motoboy == null){
                                                    cor = "white"
                                                }
                                                else if(cardData.status == "INICIAR"){
                                                     cor = "green"
                                                }
                                                else if(cardData.status == "ANDAMENTO"){
                                                    cor = "orange"
                                                }
                                                else if(cardData.status == "FINALIZADO"){
                                                    cor = "red"
                                                }
                                                let novoElemento = document.createElement('div')
                        
                                                novoElemento.innerHTML = `<div id="card" class="card${cardData.id}" onclick="desenharLocalizacaoEntrega(${cardData.localizacaoEntrega.latitude},${cardData.localizacaoEntrega.longitude},${cardData.nomePedido})" onmousedown="iniciarArrastar(event, '.card${cardData.id}')">
                                                    <h3 style="color:${cor};">${cardData.nomePedido}</h3>
                                                    <button class="btndetalhe" onclick="carregarPagina('${url}pedido/detalhes/${cardData.id}')">
                                                <i class="fas fa-info-circle"></i>
                                              </button>
                                                    <p>${cardData.localDestino}</p>
                                                  \s
                                                </div>`
                                                cardContainer.appendChild(novoElemento);
                        
                                            });
                                            elemento.textContent = `página ${page + 1} de ${pageMax}`;
                        
                                            hideLoaderIcon()
                                            document.getElementById('cards').style.display = 'block'
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
                        
                                                const li = document.createElement('div');
                                                li.innerHTML = `<h3>${item.nomeMotoboy}</h3>
                                                                                                           <p>Valor: R$ ${item.valor}</p>
                                                                                                            <p>Entregas: ${item.entregas}</p>
                                                                                                           <p>KM: ${item.km}</p>
                                                                                                \\s
                                                                                                     \\s
                                                                                                       <button class="btn-detalhe" onclick="carregarPagina('${url}historico/detalhes/${item.id}')">
                                                                                                             <i class="fas fa-info-circle"></i>
                                                                                                           </button>
                                                                                               \\s
                                                                                                           `;
                                                historicoList.appendChild(li);
                                            });
                                        })
                                        .catch(error => {
                                            console.error('Erro:', error);
                                        });
                                };
                        
                                function joinPedidoMotoboy(idPedido, idMotoboy) {
                                    fetch(`${urlRoot}/pedidos/joinMotoboy_pedido/${idPedido}/${idMotoboy}`, {
                                        method: 'GET',
                                        headers: {
                                            'Authorization': `Bearer ${token}`,
                                            'Content-Type': 'application/json'
                                        }
                                    }).then(response => response.json)
                                        .catch(error => {
                                            console.log(error);
                                        })
                                }
                        
                        
                        
                            </script>
                        </body>
                        
                        </html>
                """;
    }
}
