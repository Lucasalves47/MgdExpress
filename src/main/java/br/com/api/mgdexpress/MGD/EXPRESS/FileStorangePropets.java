package br.com.api.mgdexpress.MGD.EXPRESS;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorangePropets {

    private String donwloadDir;

    public String getDonwloadDir() {
        return donwloadDir;
    }

    public void setDonwloadDir(String donwloadDir) {
        this.donwloadDir = donwloadDir;
    }
}
