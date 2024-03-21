package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.Requests;

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
                                            display: none;
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
                                
                                        button {
                                            background-color: #4caf50;
                                            color: white;
                                            padding: 10px 15px;
                                            border: none;
                                            border-radius: 4px;
                                            cursor: pointer;
                                            width: 100%;
                                        }
                                        .ifoodfildset{
                                            margin-bottom: 15px;
                                        }
                                
                                        .popUp{
                                            width: 100%;
                                            max-width: 400px;
                                            height: 300px;
                                            position: absolute;
                                            top: auto;
                                            left: auto;
                                            right: auto;
                                            bottom: auto;
                                
                                            padding: 20px;
                                            border: 1px solid #ccc;
                                            border-radius: 5px;
                                            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                                            background-color: #fff;
                                        }
                                        .link{
                                            color: rgb(26, 94, 171);
                                            text-decoration: none;
                                        }
                                        .link:hover{
                                            text-decoration: underline;
                                            cursor: pointer;
                                        }
                                        .code{
                                            size: 30px;
                                            margin-left: 36%;
                                        }
                                        #btnPopUp{
                                            margin-top: 25px;
                                        }
                                    </style>
                                </head>
                                
                                <body>
                                    <div id="registro-form">
                                        <h2>Registro</h2>
                                        <form id="registroForm">
                                       
                                       
                                            <label for="name">name:</label>
                                            <input type="text" id="name" name="name" required>
                                            
                                            <label for="CorporativeName">Estabelecimento</label>
                                            <input type="text" id="CorporativeName" name="CorporativeName" required>
                                            
                                            <label for="telefone">Telefone:</label>
                                            <input type="text" id="telefone" name="telefone" required>
                                
                                            <label for="email">Email:</label>
                                            <input type="email" id="email" name="email" required>
                                
                                            <label for="senha">Senha:</label>
                                            <input type="password" id="senha" name="senha" required>
                                
                                            <label for="cnpj">CNPJ:</label>
                                            <input type="text" id="cnpj" name="cnpj" required>
                                
                                
                                            <button type="button" onclick="enviarFormulario()">Registrar</button>
                                        </form>
                                    </div>
                                
                                    <div class="popUp" id="popUp">
                                        <h3 style="margin-left: 25%;">Codigo de Usuario</h3>
                                        <p>codigo:</p>
                                        <p class="code"> <strong>"""+ Requests.gererCodigoDeAuthorizacao() +"</strong></p>"+"""
                                        <p>Copie o codigo acima. </p>
                                         <p> agora no <a class="link" href="https://portal.ifood.com.br/apps" target="_blank"> portal do parceiro </a>, click em <strong>Ativar um aplicativo</strong> </p>
                                
                                         <button id="btnPopUp" onclick="openCadastro()">Proximo</button>
                                    </div>
                                
                                    <script>
                                        function enviarFormulario() {
                                            var formulario = document.getElementById('registroForm');
                                            var formData = new FormData(formulario);
                                
                                            fetch('"""+url+"/gerente-temporario', {"+"""
                                                method: 'POST',
                                                body: JSON.stringify(Object.fromEntries(formData)),
                                                headers: {
                                                    'Content-Type': 'application/json'
                                                }
                                            })
                                                .then(response => {
                                                    if (!response.ok) {
                                                        alert("Usuario não encontrado no Ifood  verifique o nome e o nome do estabelecimento");
                                                    }else{
                                                    window.location.href = '"""+url+"/site/gerente/cadastro/pendente'"+"""
                                                    }
                                                })
                                        }
                                
                                      
                                        function openCadastro(){
                                            document.getElementById("popUp").style.display = "none"
                                            document.getElementById("registro-form").style.display = 'block'
                                
                                       
                                        }
                                    </script>
                                
                                </body>
                                
                                </html>
                                
                """;
    }
}
