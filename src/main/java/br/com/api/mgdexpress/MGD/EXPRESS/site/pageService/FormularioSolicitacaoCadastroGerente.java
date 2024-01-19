package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class FormularioSolicitacaoCadastroGerente {

    public static String html(String url){
        return """
                <!DOCTYPE html>
                <html lang="pt-br">
                                
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Formulário de Registro</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            min-height: 100vh;
                            margin: 0;
                            background-color: #f4f4f4;
                        }
                                
                        #registro-form {
                            width: 100%;
                            max-width: 400px;
                            padding: 20px;
                            border: 1px solid #ccc;
                            border-radius: 5px;
                            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                            background-color: #fff;
                        }
                                
                        #registro-form h2 {
                            text-align: center;
                        }
                                
                        #registro-form label {
                            display: block;
                            margin-bottom: 8px;
                        }
                                
                        #registro-form input {
                            width: 100%;
                            padding: 8px;
                            margin-bottom: 15px;
                            box-sizing: border-box;
                            border: 1px solid #ccc;
                            border-radius: 4px;
                        }
                                
                        #registro-form button {
                            background-color: #4caf50;
                            color: white;
                            padding: 10px 15px;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            width: 100%;
                        }
                    </style>
                </head>
                                
                <body>
                    <div id="registro-form">
                        <h2>Registro</h2>
                        <form id="registroForm">
                            <label for="nome">Nome:</label>
                            <input type="text" id="nome" name="nome" required>
                                
                            <label for="telefone">Telefone:</label>
                            <input type="text" id="telefone" name="telefone" required>
                                
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" required>
                                
                            <label for="senha">Senha:</label>
                            <input type="password" id="senha" name="senha" required>
                                
                            <label for="nomeEstabelecimento">Nome do Estabelecimento:</label>
                            <input type="text" id="nomeEstabelecimento" name="nomeEstabelecimento" required>
                                
                            <label for="localEstabelecimento">Local do Estabelecimento:</label>
                            <input type="text" id="localEstabelecimento" name="localEstabelecimento" required>
                                
                            <button type="button" onclick="enviarFormulario()">Registrar</button>
                        </form>
                    </div>
                                
                    <script>
                        function enviarFormulario() {
                            var formulario = document.getElementById('registroForm');
                            var formData = new FormData(formulario);
                                
                            fetch('"""+url+"""
                                    /gerente-temporario', {
                                    method: 'POST',
                                    body: JSON.stringify(Object.fromEntries(formData)),
                                    headers: {
                                        'Content-Type': 'application/json'
                                    }
                                })
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error('Erro na solicitação');
                                    }
                                    window.location.href = 'mgdexpress-production-bdc8.up.railway.app/site/gerente/cadastro/pendente'
                                })
                        }
                    </script>
                                
                </body>
                                
                </html>
                                
                """;
    }
}
