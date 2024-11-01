package com.example.demo.CatFact;

import com.example.demo.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatFactQueryHandler implements Query<Void, CatFactDTO> {

    private final RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(CatFactQueryHandler.class);

    public CatFactQueryHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Void input) {
        CatFact catFact = restTemplate.getForObject("https://catfact.ninja/fact",CatFact.class);
        logger.info(catFact.toString());
        return ResponseEntity.status(HttpStatus.OK).body(new CatFactDTO(catFact));
    }
}
