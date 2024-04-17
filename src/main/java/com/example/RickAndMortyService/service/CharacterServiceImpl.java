package com.example.RickAndMortyService.service;

import com.example.RickAndMortyService.dto.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterServiceImpl extends AbstractCharacter implements CharacterService {

    private final RestTemplate restTemplate;
    private final CharacterDataTransformer transformer;

    @Autowired
    public CharacterServiceImpl(RestTemplate restTemplate, RestTemplate restTemplate1, CharacterDataTransformer transformer) {
        super(restTemplate);
        this.restTemplate = restTemplate;
        this.transformer = transformer;
    }

    @Override
    public CharacterDTO findOne(int id) {
        String url = baseUrl + "/character/" + id ;

        HttpEntity<Void> requestEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<ExternalCharacterResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, ExternalCharacterResponse.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return transformer.transformToCharacterDTO(response.getBody());
        }

        throw new RuntimeException("Error");
    }
}
