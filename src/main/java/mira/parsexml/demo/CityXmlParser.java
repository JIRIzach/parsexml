package mira.parsexml.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import mira.parsexml.demo.data.CastObce;
import mira.parsexml.demo.data.Obec;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class CityXmlParser {
    public JsonNode parse(String xml) throws JsonProcessingException {
        log.info("Parse xml");
        XmlMapper mapper = new XmlMapper();
        return mapper.readTree(xml);
    }

    public List<Obec> getObecList(JsonNode json) {
        log.info("Get city");
        List<Obec> obecList = new LinkedList<>();
        for (JsonNode obec : json.get("Data").get("Obce")) {
            String kod = obec.get("Kod").asText();
            String nazev = obec.get("Nazev").asText();
            obecList.add(new Obec(kod, nazev));
        }
        return obecList;
    }

    public List<CastObce> getCastObceList(JsonNode json) {
        log.info("Get district");
        List<CastObce> castObceList = new LinkedList<>();
        for (JsonNode castiObci : json.get("Data").get("CastiObci")) {
            for (JsonNode castObce : castiObci) {
                String kod = castObce.get("Kod").asText();
                String nazev = castObce.get("Nazev").asText();
                String kodObce = castObce.get("Obec").get("Kod").asText();
                castObceList.add(new CastObce(kod, nazev, kodObce));
            }
        }
        return castObceList;
    }
}
