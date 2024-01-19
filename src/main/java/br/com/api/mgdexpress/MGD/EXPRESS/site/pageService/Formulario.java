package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class Formulario {

    public static String formulario(String url){
        return  """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Formulário de Pedido</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f4;
                            margin: 0;
                            padding: 0;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            min-height: 100vh;
                        }
                               
                        form {
                            background-color: #fff;
                            padding: 20px;
                            border-radius: 8px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                            width: 100%;
                            max-width: 400px;
                        }
                               
                        label {
                            display: block;
                            margin-bottom: 8px;
                        }
                             
                        input,
                        select,
                        textarea {
                            width: 100%;
                            padding: 8px;
                            margin-bottom: 16px;
                            box-sizing: border-box;
                            border: 1px solid #ccc;
                            border-radius: 4px;
                        }
                                
                        button {
                            background-color: #4caf50;
                            color: #fff;
                            padding: 10px;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            width: 100%;
                        }
                               \s
                        button:hover {
                            background-color: #45a049;
                        }
                                
                        @media (min-width: 768px) {
                            /* Ajuste de layout para telas maiores, por exemplo, notebooks */
                            form {
                                width: 400px;
                            }
                        }
                    </style>
                </head>
                                
                <body>
                    <form id="pedidoForm">
                        <label for="nomePedido">Nome Do Pedido:</label>
                        <input type="text" id="nomePedido" name="nomePedido" required />
                                   
                        <label for="localDestino">Local de Destino:</label>
                        <input type="text" id="localDestino" name="localDestino" required />
                                    
                        <label for="valor">Valor:</label>
                        <input type="number" id="valor" name="valor" required />
                                    
                        <label for="observacao">Observação:</label>
                        <textarea id="observacao" name="observacao" rows="8" cols="50"></textarea>
                                      
                        <label for="itensDoPedido">Itens do Pedido:</label>
                        <textarea id="itensDoPedido" name="itensDoPedido" placeholder="Digite seu texto aqui..." rows="8" cols="50"></textarea>
                                       
                        <button type="button" onclick="enviarPedido()">Criar</button>
                        <button style="background-color: #808080;margin-top: 28px;"  onclick="carregarPagina('"""+url+"""
                    /site/gerente/home')">Cancelar</button>
                    </form>
                    
                </body>
                </html>
                                
                """;
    }
}
