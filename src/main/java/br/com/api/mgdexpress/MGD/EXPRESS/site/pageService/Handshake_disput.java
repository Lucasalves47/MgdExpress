package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class Handshake_disput {

    public static String html(String url){
        return """
                
                <head>
                  <title>Histórico</title>
                  <style>
                      body {
                          font-family: Arial, sans-serif;
                          margin: 0;
                          padding: 0;
                          background-color: #f4f4f4;
                      }
                                
                     nav {
                   background-color: #333;
                   color: white;
                   padding: 10px;
                   text-align: center;
                   display: flex;
                   align-items: center;
                }
                                
                      ul {
                          list-style-type: none;
                          padding: 20px;
                          margin: 0;
                          display: flex;
                          flex-direction: column;
                                
                      }
                                
                      li {
                          background-color: #fff;
                          border-radius: 8px;
                          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                          margin: 10px;
                          padding: 20px;
                          box-sizing: border-box;
                                
                          justify-content: space-between;
                          align-items: center;
                      }
                                
                      button {
                          background-color: #4caf50;
                          color: white;
                          padding: 8px;
                          border: none;
                          border-radius: 4px;
                          cursor: pointer;
                      }
                      h2{margin-left: 40%;}
                                
                      .handshake_disput{
                        display: flex;
                        padding: 40px;
                        border: #333 solid 1px;
                        margin: 1%;
                        border-radius: 5px;
                      }
                                
                      .handshake_disput a{
                        margin-left: auto;
                      }
                                
                      .handshake_disput p{
                        margin-left: 30px;
                      }
                  </style>
                </head>
                                
                                
                <nav>
                   <div>
                    <p class="backLink" onclick="carregarPagina('"""+url+"/site/gerente/home')"+"""
                    ">←</p>
                  </div>
                  <h2>Solicitações de cancelamento</h2>
                </nav>
                                
                <ul id="handshake_disput-list">
                 
                </ul>
                                
                </html>""";
    }
}
