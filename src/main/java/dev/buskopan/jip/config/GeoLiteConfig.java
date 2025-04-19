package dev.buskopan.jip.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Configuration
public class GeoLiteConfig {

    @Bean(name = "cityDbReader", destroyMethod = "close")
    public DatabaseReader getCityDbReader() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("db/city.mmdb");

        if (is == null) throw new FileNotFoundException("city.mmdb not found");

        Path tempFile = Files.createTempFile("city-geolite", ".mmdb");
        tempFile.toFile().deleteOnExit();

        // Copy from input stream to temp file, replacing content when it exists
        Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);

        return new DatabaseReader.Builder(tempFile.toFile()).build();
    }

    @Bean(name = "asnDbReader", destroyMethod = "close")
    public DatabaseReader getASNDbReader() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("db/asn.mmdb");

        if (is == null) throw new FileNotFoundException("asn.mmdb not found");

        Path tempFile = Files.createTempFile("asn-geolite", ".mmdb");
        tempFile.toFile().deleteOnExit();

        // Copy from input stream to temp file, replacing content when it exists
        Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);

        return new DatabaseReader.Builder(tempFile.toFile()).build();
    }

    @Bean
    public GeoLiteBundler geoLiteBundler() throws IOException {
        return new GeoLiteBundler(getCityDbReader(), getASNDbReader());
    }
}
