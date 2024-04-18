package com.example.RickAndMortyService.Repository;

import com.example.RickAndMortyService.service.ExternalCharacterResponse;
import com.example.RickAndMortyService.service.ExternalLocationResponse;

public interface CharacterRepository {
    ExternalCharacterResponse findCharacterById(int id);
    ExternalLocationResponse findLocationById(int id);
}
