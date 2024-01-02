package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class EmConstrucao {
    public static String html(){
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Em Construção</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            text-align: center;
                            padding: 50px;
                        }
                                
                        h1 {
                            color: #555;
                        }
                                
                        p {
                            color: #777;
                        }
                                
                        img {
                            max-width: 100%;
                            height: auto;
                            margin-top: 20px;
                        }
                    </style>
                </head>
                <body>
                    <h1>Em Construção</h1>
                    <p>Esta página está em construção. Em breve estará disponível.</p>
                    <img src="under-construction.png" alt="Em Construção">
                </body>
                </html>
                """;
    }
}
