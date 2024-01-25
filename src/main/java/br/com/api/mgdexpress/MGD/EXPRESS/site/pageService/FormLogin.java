package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;


public class FormLogin {

    public static String page(String url){
        return """
                                
               
                     <!DOCTYPE html>
                     <html lang="pt-br">
                     
                     <head>
                         <meta charset="UTF-8">
                         <meta name="viewport" content="width=device-width, initial-scale=1.0">
                         <title>Login Page</title>
                         <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
                         <style>
                             body {
                                 font-family: Arial, sans-serif;
                                 display: flex;
                                 justify-content: center;
                                 align-items: center;
                                 height: 100vh;
                                 margin: 0;
                             }
                     
                             #login-form {
                                 width: 80%;
                                 max-width: 300px;
                                 padding: 20px;
                                 border: 1px solid #ccc;
                                 border-radius: 5px;
                                 box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                             }
                     
                             #login-form label {
                                 display: block;
                                 margin-bottom: 8px;
                             }
                     
                             #login-form input {
                                 width: 100%;
                                 padding: 8px;
                                 margin-bottom: 15px;
                                 box-sizing: border-box;
                             }
                     
                             #login-form button {
                                 background-color: #4caf50;
                                 color: white;
                                 padding: 10px 15px;
                                 border: none;
                                 border-radius: 4px;
                                 cursor: pointer;
                                 margin-right: 10px;
                             }
                     
                             #login-form a {
                                 text-decoration: none;
                                 color: #4caf50;
                                 display: block;
                                 margin-top: 10px;
                             }
                     
                             /* Media Queries para tornar o layout responsivo */
                             @media only screen and (max-width: 600px) {
                                 #login-form {
                                     width: 90%;
                                 }
                             }
                     
                             @media only screen and (max-width: 400px) {
                                 #login-form {
                                     width: 100%;
                                     padding: 15px;
                                 }
                                 #login-form button {
                                     margin-right: 0; /* Remove a margem à direita do botão */
                                 }
                             }
                         </style>
                     </head>
                     
                     <body>
                         <div id="login-form">
                             <h2>Login</h2>
                             <form id="loginForm">
                                 <label for="username">E-mail:</label>
                                 <input type="text" id="username" name="username" required>
                     
                                 <label for="password">Senha:</label>
                                 <input type="password" id="password" name="password" required>
                     
                                 <button type="button" onclick="login()">Login</button>
                     
                                 <a href='"""+url+"/site/gerente/solicitacao/cadastro'>Cadastre-se</a>"+"""
                            
                             </form>
                         </div>
                     </body>
                     
                     </html>
                     
                            
                """;
    }
}
