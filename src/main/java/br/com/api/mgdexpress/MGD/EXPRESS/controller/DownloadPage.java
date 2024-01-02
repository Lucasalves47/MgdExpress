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
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping()
public class DownloadPage {

    private static final String DOWNLOADS_DIR = "br/com/api/mgdexpress/MGD/EXPRESS/apk";
    private static final String FILE_NAME = "app-release.apk";

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile() throws IOException {
        // Constrói o caminho completo do arquivo
        Path filePath = Paths.get(DOWNLOADS_DIR, FILE_NAME);

        // Verifica se o arquivo existe
        if (Files.exists(filePath)) {
            // Lê o conteúdo do arquivo em um array de bytes
            byte[] content = Files.readAllBytes(filePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", FILE_NAME);

            return new ResponseEntity<>(content, headers, HttpStatus.OK);
        } else {
            // Retorna uma resposta 404 se o arquivo não for encontrado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
