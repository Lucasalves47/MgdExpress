package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/img")
public class imgController {

    @GetMapping("/iconeMoto")
    public String logo(){
        return """
                """;
    }

}
