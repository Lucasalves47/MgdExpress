package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class ListarMeusPedidos {

    public static String listar(String url){
        return """
                               
                <head>
                    <title>Meus Pedidos</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 0;
                            padding: 0;
                            background-color: #f4f4f4;
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
                                       
                        .card {
                            border: 1px solid #ccc;
                            border-radius: 8px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                            margin: 10px;
                            width: 300px;
                            float: left;
                            background-color: #fff;
                        }
                                       
                        .card img {
                            width: 100%;
                            height: 150px;
                            object-fit: cover;
                            border-top-left-radius: 8px;
                            border-top-right-radius: 8px;
                        }
                                       
                        .card-content {
                            padding: 10px;
                            text-align: left;
                        }
                        .titulo{
                            text-align: center;
                        }
                                       
                        button {
                            background-color: #4caf50;
                            color: #fff;
                            padding: 8px;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            width: 100%;
                        }
                                       
                        button:hover {
                            background-color: #45a049;
                        }
                                       
                        @media (max-width: 768px) {
                            .card {
                                width: calc(50% - 20px);
                            }
                        }
                                       
                        nav a {
                            color: white;
                            text-decoration: none;
                            margin-left: 15px;
                        }
                                       
                        .backLink{
                            font-size : 160%;
                        }
                        h2{margin-left: 40%;}
                    </style>
                </head>
                                       
                                       
                <nav>
               <h2>MGD EXPRESS</h2>
               <div>
                       <button class="btnNav" onclick="clearInterval(intervalId);carregarPagina('"""+url+"/site/gerente/criar')"+"""
                           ">Novo Pedido</button>
                      
                       <button class="btnNav" onclick="clearInterval(intervalId);listarHistoricoEntregas();">Entregas do dia</button>
                                       
                       <button class="btnNav" onclick="clearInterval(intervalId);listarHistorico();">Histórico</button>
                   </div>
               </nav>
                                       
                <div id="card-container"></div>
                
              
                """;
    }
}
