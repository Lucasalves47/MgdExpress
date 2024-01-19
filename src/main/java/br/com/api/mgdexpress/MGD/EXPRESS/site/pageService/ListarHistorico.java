package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.Historico;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListarHistorico {
    @Autowired
    private HistoricoRepository historicoRepository;
    public static String historocos(String email) {

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
                           </style>
                       </head>
                       
                       
                       <nav>
                            <div>
                                <p class="backLink" onclick='carregarPagina("mgdexpress-production-bdc8.up.railway.app/site/gerente/home")'>←</p>         
                           </div>
                           <h2>Histórico</h2>
                       </nav>
                       
                       <ul id="historico-list"></ul>
                    
                """;
    }
}
