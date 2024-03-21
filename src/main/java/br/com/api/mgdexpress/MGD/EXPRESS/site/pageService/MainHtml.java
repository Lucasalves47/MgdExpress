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
                                                   var idMotoboy = buscarMOtoboyDisponivel();
                                                   joinPedidoMotoboy(idMotoboy, cardData.id)
                                                }
                                                else if(cardData.status == "INICIAR"){
                                                     cor = "green"
                                                     var urli = "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
                                                     desenharTodasLocalizacaoEntrega(cardData.localizacaoEntrega.latitude,cardData.localizacaoEntrega.longitude,cardData.nomePedido,30,urli)
                                                }
                                                else if(cardData.status == "IFOODACEITO"){
                                                    cor = "green"
                                                    var urli = "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
                                                    desenharTodasLocalizacaoEntrega(cardData.localizacaoEntrega.latitude,cardData.localizacaoEntrega.longitude,cardData.nomePedido,30,urli)
                                                }
                                                else if(cardData.status == "ANDAMENTO"){
                                                    cor = "orange"
                                                    var urli = "http://maps.google.com/mapfiles/ms/icons/orange-dot.png"
                                                     desenharTodasLocalizacaoEntrega(cardData.localizacaoEntrega.latitude,cardData.localizacaoEntrega.longitude,cardData.nomePedido,30,urli)
                                                }
                                                else if(cardData.status == "FINALIZADO"){
                                                    cor = "yellow"
                                                    var urli = "http://maps.google.com/mapfiles/ms/icons/red-dot.png"
                                                     desenharTodasLocalizacaoEntrega(cardData.localizacaoEntrega.latitude,cardData.localizacaoEntrega.longitude,cardData.nomePedido,30,urli)
                                                }
                                                else if(cardData.status == "CANCELADO"){
                                                    cor = "del"
                                                    
                                                    
                                                }
                                               
                                                
                                               
                                                let novoElemento = document.createElement('div')
                                                                                        
                                             if(cor == 'del'){
                                             novoElemento.innerHTML = `<div id="card" class="card${cardData.id}" onclick="desenharLocalizacaoEntrega(${cardData.localizacaoEntrega.latitude},${cardData.localizacaoEntrega.longitude},${cardData.nomePedido},80)" onmousedown="iniciarArrastar(event, '.card${cardData.id}')">
                                                                 <h3 style="color:red;"><del>${cardData.nomePedido}</del></h3>
                                                                 <button class="btndetalhe" onclick="carregarPagina('${url}pedido/detalhes/${cardData.id}')">
                                                             <i class="fas fa-info-circle"></i>
                                                           </button>
                                                                 <p>${cardData.localDestino}</p>
                                                               
                                                             </div>`
                                                                                            }
                                                                                            
                                            
                                             
                                             
                                                                                            else{
                                             novoElemento.innerHTML = `<div id="card" class="card${cardData.id}" onclick="desenharLocalizacaoEntrega(${cardData.localizacaoEntrega.latitude},${cardData.localizacaoEntrega.longitude},${cardData.nomePedido},80)" onmousedown="iniciarArrastar(event, '.card${cardData.id}')">
                                                                 <h3 style="color:${cor};">${cardData.nomePedido}</h3>
                                                                 <button class="btndetalhe" onclick="carregarPagina('${url}pedido/detalhes/${cardData.id}')">
                                                             <i class="fas fa-info-circle"></i>
                                                           </button>
                                                                 <p>${cardData.localDestino}</p>
                                                               
                                                             </div>`
                                                                                            }
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
                                
                                
                                function status() {
                                    fetch(`${urlRoot}/ifood/status`, {
                                        method: 'GET',
                                        headers: {
                                            'Authorization': `Bearer ${token}`,
                                            'Content-Type': 'application/json'
                                        }
                                    })
                                        .then(response => response.json())
                                        .then(data => status_(data.status))
                                        .catch(error => console.error('Erro na requisição:', error));
                                }
                        
                        
                        
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
                       
                                    buscarHistoricoEntregas("")
                                    
                                };
                                
                                function buscarHistoricoEntregas(comanda){
                                fetch(`${urlRoot}/gerente/listarMotoboysEntregas/${comanda}`, {
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
                                             
                                                  
                                                    <button class="btn-detalhe" onclick="carregarPagina('${url}historico/detalhes/${item.id}')">
                                                          <i class="fas fa-info-circle"></i>
                                                        </button>
                                                                                               
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
                                
                                function aceitarPedidoIfood(idPedido){
                                            fetch(`${urlRoot}/ifood/confirm/${idPedido}`, {
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
                                        
                                function dispachar(idPedido){
                                            fetch(`${urlRoot}/ifood/dispatch/${idPedido}`, {
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
                                        
                                function takeoutIfood(idPedido){
                                            fetch(`${urlRoot}/ifood/takeout/${idPedido}`, {
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
                                        
                                function apagarPedido(idPedido){
                                            fetch(`${urlRoot}/ifood/apagarPedido/${idPedido}`, {
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
                                        
                                function motivosCancelamento(idPedido){
                                            fetch(`${urlRoot}/ifood/cancelarPedidoIfoodMotivos/${idPedido}`, {
                                                method: 'GET',
                                                headers: {
                                                    'Authorization': `Bearer ${token}`,
                                                    'Content-Type': 'application/json'
                                                }
                                            }).then(response => response.json()
                                            .then(data=>abrirPopUp(data)))
                                            
                                                .catch(error => {
                                                    console.log(error);
                                                })
                                        }
                                        
                                         function aceitarProposta(idDisput){
                                            fetch(`${urlRoot}/plataformaDenegociaçaoPedido/aceitar/${idDisput}`, {
                                                method: 'GET',
                                                headers: {
                                                    'Authorization': `Bearer ${token}`,
                                                    'Content-Type': 'application/json'
                                                }
                                            }).then(response => response.json()
                                            .then(data=>abrirPopUp(data)))
                                            
                                                .catch(error => {
                                                    console.log(error);
                                                })
                                        }
                                        
                                        function regeitarProposta(idDisput,reason){
                                            fetch(`${urlRoot}/plataformaDenegociaçaoPedido/rejeitar/${idDisput}/${reason}`, {
                                                method: 'GET',
                                                headers: {
                                                    'Authorization': `Bearer ${token}`,
                                                    'Content-Type': 'application/json'
                                                }
                                            }).then(response => response.json()
                                            .then(data=>abrirPopUp(data)))
                                            
                                                .catch(error => {
                                                    console.log(error);
                                                })
                                        }
                                        
                                        function contraProposta(disputeId,alternativeId,type){
                                            fetch(`${urlRoot}/plataformaDenegociaçaoPedido/alternative/${disputeId}/${alternativeId}/${type}`, {
                                                method: 'GET',
                                                headers: {
                                                    'Authorization': `Bearer ${token}`,
                                                    'Content-Type': 'application/json'
                                                }
                                            }).then(response => response.json()
                                            .then(data=>abrirPopUp(data)))
                                            
                                                .catch(error => {
                                                    console.log(error);
                                                })
                                        }
                                        
                                        
                                        function criarPedidoSemDados(){
                                                fetch(`${urlRoot}/pedidos/peiddosSemInformacao`, {
                                                    method: 'GET',
                                                    headers: {
                                                        'Authorization': `Bearer ${token}`,
                                                        'Content-Type': 'application/json'
                                                    }
                                                }).then(response => response.json())
                                               
                                                    .catch(error => {
                                                        console.log(error);
                                                    })
                                            }
                                            
                                        async function buscarMotoboyDisponivel() {
                                        try {
                                            const response = await fetch(`${urlRoot}/motoboy/buscarMotoboyDisponive`, {
                                                method: 'GET',
                                                headers: {
                                                    'Authorization': `Bearer ${token}`,
                                                    'Content-Type': 'application/json'
                                                }
                                            });
                                            const data = await response.json();
                                            return data.id;
                                        } catch (error) {
                                            console.log(error);
                                        }
                                    }
                                        
                                         function cancelarPeidoIfood(dadosCancelamento,idPedido){
                                            
                                        }
              
              
              //**************ifood detalhe de pedidos
              
                   
            // Get the button that opens the popup
            var openBtn = document.getElementById("openPopup");
                   
            // Get the <span> element that closes the popup
            var closeBtn = document.getElementById("closePopup");                         
                                        
            // When the user clicks on <span> (x), close the popup
            closeBtn.onclick = function () {
                popup.style.display = "none";
            }
                   
            // Close the popup when the user clicks outside of it
            window.onclick = function (event) {
                if (event.target == popup) {
                    popup.style.display = "none";
                }
            }
                                        
                                        
                                        
                          function abrirPopUp(dados) {
                             
                            popup.style.display = "block";
                            var divPopUp = document.getElementById("dadospopup")
                            var div = document.createElement("div");
                            dados.forEach(dado => {
                                div.innerHTML += `<input type="radio" id="opcao1" name="opcoes" value="${dado.cancelCodeId}°-°${dado.description}">
                    <label for="opcao1">${dado.description}</label><br>`
                            })
                                
                            divPopUp.appendChild(div)
                        }
                        
                        
                          function enviarBtn(idpedidoifood) {
              var opcaoSelecionada = document.querySelector('input[name="opcoes"]:checked');
             
              if (opcaoSelecionada) {
              console.log("estou funcionando "+opcaoSelecionada.value)
              fetch(`${urlRoot}/ifood/cancelar/${idpedidoifood}/${opcaoSelecionada.value}`, {
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
            }
                        
                        
                        
                        function listarHandshake_disput() {
                        
                        
                                    carregarPagina(`${url}Handshake_disput`);
                        
                                    fetch(`${urlRoot}/plataformaDenegociaçaoPedido/listar`, {
                                        method: 'GET',
                                        headers: {
                                            'Authorization': `Bearer ${token}`,
                                            'Content-Type': 'application/json'
                                        }
                                    })
                                        .then(response => response.json())
                                        .then(data => {
                                            data.forEach(item => {
                                                const handList = document.getElementById('handshake_disput-list');
                        
                                                const li = document.createElement('li');
                                                li.innerHTML = `
                                                <div class="handshake_disput">
                                                <p>pedido: ${item.nome}</p>
                                                <p>Açao: ${item.action}</p>
                                                
                                                <a onclick="carregarPagina('${url}/Handshake_disput/detalhes/${item.id}')">
                                                    <button>Detalhes</button>
                                                </a>
                                                </div>`;
                                                handList.appendChild(li);
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
