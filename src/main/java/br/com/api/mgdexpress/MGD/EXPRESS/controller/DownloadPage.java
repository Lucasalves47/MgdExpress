package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.Download;
import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.EmConstrucao;
import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.HtmlPage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class DownloadPage {

    @GetMapping
    public ResponseEntity<HtmlPage> download(){
        return ResponseEntity.ok(new HtmlPage(Download.html()));
    }
}
