package com.example.RickAndMortyService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractCharacter {

    @Value("${rickandmorty.api.url}")
    protected String baseUrl;

    protected final RestTemplate restTemplate;

    protected AbstractCharacter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
