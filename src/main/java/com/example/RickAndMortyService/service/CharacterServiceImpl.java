package com.example.RickAndMortyService.service;

import com.example.RickAndMortyService.Repository.CharacterRepository;
import com.example.RickAndMortyService.dto.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;
    private final CharacterDataTransformer transformer;

    @Autowired
    public CharacterServiceImpl(CharacterRepository repository, CharacterDataTransformer transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    @Override
    public CharacterDTO findOne(int id) {
        ExternalCharacterResponse response = repository.findCharacterById(id);
        ExternalLocationResponse location = repository.findLocationById(id);
        return transformer.transformToCharacterDTO(response, location);
    }
}
