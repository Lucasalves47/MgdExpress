package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class Download {
    public static String html(){
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Download</title>
                    <style>
                        body {
                            margin: 0;
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f4;
                            color: #333;
                        }
                                
                        .toolbar {
                            background-color: black;
                            color: white;
                            padding: 15px;
                            text-align: center;
                        }
                                
                        h1 {
                            color: #007bff;
                        }
                                
                        .content {
                            padding: 20px;
                            text-align: center;
                        }
                                
                        .download-link {
                            display: inline-block;
                            padding: 10px 20px;
                            background-color: #007bff;
                            color: white;
                            text-decoration: none;
                            border-radius: 5px;
                        }
                                
                        /* Adicionando regras de estilo responsivas */
                        @media only screen and (max-width: 600px) {
                            .download-link {
                                font-size: 14px;
                                padding: 8px 16px;
                            }
                        }
                    </style>
                </head>
                <body>
                    <div class="toolbar">
                        <h1>Download</h1>
                    </div>
                    <div class="content">
                        <p>Faça o download do arquivo abaixo:</p>
                        <a class="download-link" href="src/main/java/br/com/api/mgdexpress/MGD/EXPRESS/apk/app-release.apk" download="src/main/java/br/com/api/mgdexpress/MGD/EXPRESS/apk/app-release.apk">Download</a>
                    </div>
                </body>
                </html>
                                
                """;
    }
}
