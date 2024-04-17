package com.example.RickAndMortyService.service;

import com.example.RickAndMortyService.dto.CharacterDTO;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {

    CharacterDTO findOne(int id);
}
