package com.example.RickAndMortyService.service;

import com.example.RickAndMortyService.dto.CharacterDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CharacterDataTransformerTest {

    @Test
    public void transformToCharacterDTO_ShouldCorrectlyTransformData() {
        // Setup
        ExternalCharacterResponse characterResponse = new ExternalCharacterResponse();
        characterResponse.setId(1);
        characterResponse.setName("Rick Sanchez");
        characterResponse.setStatus("Alive");
        characterResponse.setSpecies("Human");
        characterResponse.setType("");
        characterResponse.setEpisode(Arrays.asList("E1", "E2", "E3"));

        ExternalCharacterResponse.Origin origin = new ExternalCharacterResponse.Origin();
        origin.setName("Earth (C-137)");
        origin.setUrl("https://rickandmortyapi.com/api/location/1");

        ExternalLocationResponse locationResponse = new ExternalLocationResponse();
        locationResponse.setDimension("Dimension C-137");
        locationResponse.setResidents(Arrays.asList("Character 1", "Character 2"));

        characterResponse.setOrigin(origin);

        // Act
        CharacterDataTransformer transformer = new CharacterDataTransformer();
        CharacterDTO result = transformer.transformToCharacterDTO(characterResponse, locationResponse);

        // Assert
        assertEquals(1, result.getId());
        assertEquals("Rick Sanchez", result.getName());
        assertEquals("Alive", result.getStatus());
        assertEquals("Human", result.getSpecies());
        assertEquals("", result.getType());
        assertEquals(3, result.getEpisodeCount());

        assertEquals("Earth (C-137)", result.getOrigin().getName());
        assertEquals("https://rickandmortyapi.com/api/location/1", result.getOrigin().getUrl());
        assertEquals("Dimension C-137", result.getOrigin().getDimension());
        assertEquals(Arrays.asList("Character 1", "Character 2"), result.getOrigin().getResidents());
    }

}