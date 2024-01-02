package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.Download;
import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.EmConstrucao;
import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.HtmlPage;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping()
public class DownloadPage {

    @GetMapping
    public ResponseEntity<String> download(){
        return ResponseEntity.ok(Download.html());
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        // Caminho para o arquivo
        Path filePath = Paths.get("/apk/app-release.apk");

        // Criar um recurso URL a partir do caminho do arquivo
        Resource resource = new UrlResource(filePath.toUri());

        // Configurar cabeçalhos para resposta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app-release.apk");

        // Retorna a resposta HTTP com o arquivo para download
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
