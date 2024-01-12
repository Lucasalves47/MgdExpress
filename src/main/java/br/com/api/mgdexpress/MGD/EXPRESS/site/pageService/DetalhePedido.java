package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Pedido;

public class DetalhePedido {

    public static String detalhar(){
        return """
                
                       <head>
                           <title>Detalhe do pedido</title>
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
                               }
                               h2{margin-left: 40%;}
                           </style>
                       </head>
                       
                       
                       <nav>
                       <div>
                                <p class="backLink" onclick='carregarPagina("https://mgdexpress-production.up.railway.app/site/gerente/home")'>←</p>         
                           </div>
                           <h2>Detalhe do Pedido</h2>
                       </nav>
                       
                       <main>
                           <div class=card_pedido">
                              
                           </div>
                       </main>
                """;
    }
}
