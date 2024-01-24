package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class ListarMeusPedidos {

    public static String listar(String url){
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
                                        <button onclick="clearInterval(intervalId);carregarPagina('""\"+url+"/site/gerente/criar')"+""\"
                                            ">Novo Pedido</button>
                                        <button onclick="clearInterval(intervalId);listarPedidos();">Meus Pedidos</button>
                                        <button onclick="clearInterval(intervalId);listarHistoricoEntregas();">Entregas do dia</button>
                                      
                                        <button onclick="clearInterval(intervalId);listarHistorico();">Histórico</button>
                                    </div>
                                </nav>
                                               
                                               
                                <body>
                                    <div id="card-container"></div>
                                </body>
                               
                                <script>
                                    listarPedidos()
                                    </script>
                """;
    }
}
