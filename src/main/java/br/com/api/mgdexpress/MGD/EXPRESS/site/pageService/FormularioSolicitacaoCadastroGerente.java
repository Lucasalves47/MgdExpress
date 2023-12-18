package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class FormularioSolicitacaoCadastroGerente {

    public static String html(){
        return """
                <!DOCTYPE html>
                       <html lang="pt-br">
                       <head>
                           <meta charset="UTF-8">
                           <title>Formulário de Registro</title>
                           <style>
                               body {
                                   font-family: Arial, sans-serif;
                                   display: flex;
                                   justify-content: center;
                                   align-items: center;
                                   height: 100vh;
                                   margin: 0;
                               }
                       
                               #registro-form {
                                   width: 300px;
                                   padding: 20px;
                                   border: 1px solid #ccc;
                                   border-radius: 5px;
                                   box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
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
                               }
                       
                               #registro-form button {
                                   background-color: #4caf50;
                                   color: white;
                                   padding: 10px 15px;
                                   border: none;
                                   border-radius: 4px;
                                   cursor: pointer;
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
                       
                               fetch('https://mgdexpressapi-production.up.railway.app/gerente-temporario', {
                                   method: 'POST',
                                   body:  JSON.stringify(Object.fromEntries(formData)),
                                   headers: {
                                       'Content-Type': 'application/json'
                                   }
                               })
                               .then(response => {
                                   if (!response.ok) {
                                       throw new Error('Erro na solicitação');
                                   }
                                   return response.json();
                               })
                               .then(data => {
                                   // Manipular a resposta aqui, se necessário
                                   window.location.href = 'https://mgdexpressapi-production.up.railway.app/site/gerente/cadastro/pendente'
                               })
                               .catch(error => {
                                   console.log(error);
                               });
                           }
                       </script>
                       
                       </body>
                       </html>
                """;
    }
}
