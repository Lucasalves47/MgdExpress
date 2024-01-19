package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.users.Login;
import br.com.api.mgdexpress.MGD.EXPRESS.model.users.User;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import br.com.api.mgdexpress.MGD.EXPRESS.site.ControllerSiteGerente;
import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.FormLogin;
import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public String login(@RequestBody Login login){

        var usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.username(),login.password());
        var authenticate =
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var usuario = (User) authenticate.getPrincipal();


        return tokenService.gerarToken(usuario);
    }


    @GetMapping
    public ResponseEntity login(){

        return ResponseEntity.ok(new HtmlPage(FormLogin.page(new ControllerSiteGerente().url)));
    }

}
