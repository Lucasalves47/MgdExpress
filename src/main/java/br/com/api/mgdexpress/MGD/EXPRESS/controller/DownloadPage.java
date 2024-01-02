package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.apk.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping()
public class DownloadPage {

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile() throws IOException {
        // Substitua "arquivo.txt" pelo caminho do seu arquivo real
        Resource resource = new ClassPathResource("src/main/java/br/com/api/mgdexpress/MGD/EXPRESS/apk/app-release.apk");

        // Lê o conteúdo do arquivo em um array de bytes
        byte[] content = Files.readAllBytes(resource.getFile().toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "arquivo.txt");

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
}
