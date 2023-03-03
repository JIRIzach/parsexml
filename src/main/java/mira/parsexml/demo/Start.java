package mira.parsexml.demo;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import mira.parsexml.demo.data.CastObce;
import mira.parsexml.demo.data.Obec;
import mira.parsexml.demo.repository.CastObceRepository;
import mira.parsexml.demo.repository.ObecRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
@Slf4j
public class Start implements CommandLineRunner {

    private static final String SOURCE_URL = "https://www.smartform.cz/download/kopidlno.xml.zip";
    private final WebGet webGet;
    private final CityXmlParser parser;
    private final ObecRepository obecRepository;
    private final CastObceRepository castObceRepository;

    public Start(WebGet webGet, CityXmlParser parser, ObecRepository obecRepository, CastObceRepository castObceRepository) {
        this.webGet = webGet;
        this.parser = parser;
        this.obecRepository = obecRepository;
        this.castObceRepository = castObceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        URL url = new URL(SOURCE_URL);
        // download xml
        String xml = webGet.get(url);
        // parse xml
        JsonNode json = parser.parse(xml);
        List<Obec> obecList = parser.getObecList(json);
        List<CastObce> castObceList = parser.getCastObceList(json);
        // feed into DB
        obecRepository.saveAll(obecList);
        obecRepository.flush();
        log.info("Persisted " + obecList.size() + " cities");
        castObceRepository.saveAll(castObceList);
        castObceRepository.flush();
        log.info("Persisted " + castObceList.size() + " districts");
    }
}
