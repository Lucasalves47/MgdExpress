package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class FormLogin {

    public static String page(){
        return """
                                
                <head>
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
                            width: 300px;
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
                    </form>
                </div>
                """;
    }
}
