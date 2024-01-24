package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class Home {

    public static String home(String url){
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
                                
             
                <script>
                    listarPedidos()
                    </script>
                    
              """;
    }
}


