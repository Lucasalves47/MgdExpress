package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Pedido;
import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Status;

public class DetalhePedido {
    public static String detalhar(Pedido dados,String url){
        String funcaoJs;
        String textBtn;
        String bandeiraDoCartao = "";
        String observacoa = "";

        if (dados.getStatus() == Status.IFOODACEITO) {
            funcaoJs = "dispachar";
            textBtn = "dispachar";
        }
        else if(dados.getStatus().equals(Status.CANCELADO)){
            funcaoJs= "apagarPedido";
            textBtn = "deletar";
        }
        else {
            funcaoJs  = "aceitarPedidoIfood";
            textBtn = "aceitar";
        }

        if(dados.getOrderType().equals("TAKEOUT") && dados.getStatus() == Status.IFOODACEITO){
            funcaoJs  = "takeoutIfood";
            textBtn = "pronto";
        }

        if(dados.getCard() != null){
            bandeiraDoCartao = "bandeira do cartao :"+dados.getCard();
        }





        return """
                <!DOCTYPE html>
                <html lang="pt-br">
                                
                <head>
                    <meta charset="UTF-8">
                    <title>Detalhe do Histórico</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 0;
                            padding: 0;
                            background-color: #f4f4f4;
                            color: #333;
                        }
                                
                        nav {
                            background-color: #333;
                            color: white;
                            padding: 10px;
                            text-align: center;
                            display: flex;
                            align-items: center;
                        }
                                
                        main {
                            padding: 20px;
                        }
                                
                        .card {
                            background-color: #fff;
                            border-radius: 8px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                            margin-bottom: 20px;
                            padding: 20px;
                        }
                                
                        .card p {
                            margin: 0;
                            margin-bottom: 10px;
                        }
                                
                        .card h3 {
                            margin-top: 0;
                            color: #4caf50;
                        }
                                
                        .backLink {
                            cursor: pointer;
                        }
                                
                        h2 {
                            margin-left: 40%;
                            color: #fff;
                                
                        }
                                
                        ul {
                            list-style-type: none;
                            padding: 0;
                        }
                                
                        li {
                            margin-bottom: 10px;
                        }
                        .ifood{
                            display: flex;
                          
                        }
                                
                        button {
                            background-color: #4CAF50;
                            color: white;
                            padding: 10px 20px;
                            border: none;
                            border-radius: 5px;
                            cursor: pointer;
                            font-size: 16px;
                            margin-left: 15px;
                          
                        }
                        .btnAceitar{
                            margin-left: 40px;
                            margin-right: auto;
                        }
                        .btnRejeitar{
                            margin-right: 40px;
                            margin-left: auto;
                        }
                                
                        .btnRejeitar:hover{
                           background-color: red;
                        }
                                
                        .btnAceitar:hover{
                            background-color: #008704;
                        }
                        
                         .itensIfood{
                                    display: flex;
                                }
                         .itensIfood p{
                             margin-left: 8%;
                                  
                             }
                             
                     .popup {
                                 display: none;
                                 /* Hidden by default */
                                 position: fixed;
                                 /* Stay in place */
                                 z-index: 1;
                                 /* Sit on top */
                                 left: 0;
                                 top: 0;
                                 width: 100%;
                                 /* Full width */
                                 height: 100%;
                                 /* Full height */
                                 overflow: auto;
                                 /* Enable scroll if needed */
                                 background-color: rgba(0, 0, 0, 0.4);
                                 /* Black w/ opacity */
                             }
                     
                             .popup-content {
                                 background-color: #fefefe;
                                 margin: 15% auto;
                                 padding: 20px;
                                 border: 1px solid #888;
                                 width: 80%;
                             }
                     
                             .close {
                                 color: #aaa;
                                 float: right;
                                 font-size: 28px;
                                 font-weight: bold;
                             }
                     
                             .close:hover,
                             .close:focus {
                                 color: black;
                                 text-decoration: none;
                                 cursor: pointer;
                             }
                     
                             .popup-content .dadospopup {
                                 margin-left: auto;
                                 margin-right: auto;
                                 display: block;
                             }
                    </style>
                </head>
                                
                <body>
                    <nav>
                        <div>
                            <p class="backLink" onclick='carregarPagina(\""""+url+"/site/gerente/home\")'"+"""
                        >←</p>
                        </div>
                        <h2>Detalhe do Pedido</h2>
                    </nav>
                                
                    <main>
                        <div class="card">
                            <h3>Pedido</h3>
                            <p>Nome do Estabelecimento:""" + dados.getNomeEstabelecimento() + """
                            </p>
                            <p>Local de Origem :""" + dados.getLocalOrigem() + """
                            </p>
                            <p>Local de Destino :""" + dados.getLocalDestino() + """
                            </p>
                            <p>Valor :""" + dados.getValorDoPedido() + """
                            </p>
                            """+dados.getBeneficios()+ """
               
                            <p>Metodo De Pagamento :""" + dados.getMetodoPagamento() + """
                            </p>
                            <p>""" + bandeiraDoCartao + """
    </p>
                            <p>Troco :""" + dados.getTroco() + """
                            </p>
                            <h4>Itens</h4>
                            
                                """ +dados.getObservacao()+"""
            
                            
                            <p>Iniciar em :""" +dados.getPedidoAgendadoStart() + """
                            <p>Entregar em :""" + dados.getPedidoAgendadoEnd() + """
                            </p>
                                
                            <h3>Gerente:</h3>
                            <ul>
                            
                                <li>Nome :""" + dados.getGerente().getNome() + """
                                </li>
                                <li>cpf/cnpj :""" + dados.getGerente().getCpfCnpj() + """
                                               
                                </li>
                                <li>Telefone :""" + dados.getGerente().getTelefone() + """
                                </li>
                                <li>Email :""" + dados.getGerente().getEmail() + """
                                
                                </li>
                                
                            </ul>
                        </div>
                        <div class="ifood">
                            <button class="btnRejeitar" onclick="motivosCancelamento('"""+dados.getIdPedidoIfood()+"')\">cancelar</button>"+ """
                            <button class="btnAceitar" onclick=\""""+funcaoJs+"('"+dados.getIdPedidoIfood()+"')\">"+textBtn+"</button>"+"""
                        
                        </div>
                        
                        <div id="popup" class="popup">
                                <!-- Popup content -->
                                <div class="popup-content">
                                    <span class="close" id="closePopup">&times;</span>
                        
                                    <div class="dadospopup" id="dadospopup">
                                    </div>
                                    <button id="enviarBtn" class="btnRejeitar" onclick="enviarBtn('"""+dados.getIdPedidoIfood()+"')\">Enviar Cancelamento</button>"+"""
                        
                        </div>
                    </main>
                 
                </body>
                </html>
                              
                """;
    }
}
