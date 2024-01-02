package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.FileStorangePropets;
import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.Download;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/download")
public class DownloadPage {

    private final Path fileStorangeLocation;

    public DownloadPage(FileStorangePropets fileStorangePropetis) {
        this.fileStorangeLocation = Paths.get(fileStorangePropetis.getDonwloadDir()).toAbsolutePath().normalize();
    }

    @GetMapping
    public ResponseEntity<String> download(){
        return ResponseEntity.ok(Download.html());
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest reequest) throws IOException {
       Path filePath = fileStorangeLocation.resolve("app-release.apk").normalize();
       Resource resource = new UrlResource(filePath.toUri());

       String contentType = reequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
       if(contentType == null)
           contentType = "application/octet-stream";

       return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
               .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+resource.getFile()+"\"")
               .body(resource);

    }

    @GetMapping("/lista")
    public ResponseEntity<List<String>> listFile() throws IOException {
        List<String> fileName = Files.list(fileStorangeLocation).map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());

        return ResponseEntity.ok(fileName);
    }
}
