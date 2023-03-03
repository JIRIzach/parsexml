package mira.parsexml.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

@Component
@Slf4j
public class WebGet {
    public String get(URL url) throws Exception {
        log.info("HTTP GET " + url);
        var con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream is = con.getInputStream();
            ZipInputStream zipInputStream = new ZipInputStream(is);
            if (zipInputStream.getNextEntry() != null) {
                return new BufferedReader(new InputStreamReader(zipInputStream))
                        .lines()
                        .collect(Collectors.joining());
            }
        }
        throw new Exception("HTTP code: " + con.getResponseCode());
    }
}
