package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class Sucesso {

    public static String sucesso(String url){
        return """
                <head>
                                
                    <title>Sucesso</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f4;
                            text-align: center;
                            padding: 20px;
                        }
                                
                        .container {
                            max-width: 600px;
                            margin: 0 auto;
                            background-color: #fff;
                            padding: 20px;
                            border-radius: 8px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                        }
                                
                        h1 {
                            color: #333;
                        }
                                
                        p {
                            color: #666;
                            margin-bottom: 20px;
                        }
                                
                        button {
                            background-color: #3498db;
                            color: #fff;
                            padding: 10px 20px;
                            border: none;
                            border-radius: 5px;
                            font-size: 16px;
                            cursor: pointer;
                        }
                                
                        button:hover {
                            background-color: #2184b5;
                        }
                    </style>
                </head>
                                
                <div class="container">
                    <h1>Pedido Criado com Sucesso!</h1>
                    <p>Seu pedido foi registrado com sucesso. Obrigado!</p>
                    <button onclick="carregarPagina(''"""+url+"/site/gerente/home')"+"""
                >Voltar para a Home</button>
                </div>
                                
                """;
    }
}
