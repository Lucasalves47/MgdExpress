package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class Home {

    public static String home(String url){
        return """
                <head>
                    <title>MGD EXPRESS</title>
                </head>
              
                <script>
                    listarPedidos()
                    </script>
                    
                """;
    }
}


