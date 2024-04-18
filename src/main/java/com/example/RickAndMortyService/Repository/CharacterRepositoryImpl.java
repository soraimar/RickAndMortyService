package com.example.RickAndMortyService.Repository;

import com.example.RickAndMortyService.service.AbstractCharacter;
import com.example.RickAndMortyService.service.ExternalCharacterResponse;
import com.example.RickAndMortyService.service.ExternalLocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterRepositoryImpl extends AbstractCharacter implements CharacterRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public CharacterRepositoryImpl(RestTemplate restTemplate) {
        super(restTemplate);
        this.restTemplate = restTemplate;
    }

    @Override
    public ExternalCharacterResponse findCharacterById(int id) {
        return fetchFromExternalService("/character/" + id, ExternalCharacterResponse.class);
    }

    @Override
    public ExternalLocationResponse findLocationById(int id) {
        return fetchFromExternalService("/location/" + id, ExternalLocationResponse.class);
    }

    private <T> T fetchFromExternalService(String url, Class<T> responseType) {
        ResponseEntity<T> response = restTemplate.exchange(baseUrl + url,
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), responseType);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        throw new RuntimeException("Error fetching data");
    }
}

