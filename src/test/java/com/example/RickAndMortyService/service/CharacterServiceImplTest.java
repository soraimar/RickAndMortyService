package com.example.RickAndMortyService.service;

import com.example.RickAndMortyService.Repository.CharacterRepository;
import com.example.RickAndMortyService.dto.CharacterDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CharacterServiceImplTest {

    @Mock
    private CharacterRepository repository;

    @Mock
    private CharacterDataTransformer transformer;

    @InjectMocks
    private CharacterServiceImpl service;

    private ExternalCharacterResponse createMockCharacterResponse() {
        ExternalCharacterResponse response = new ExternalCharacterResponse();
        response.setId(1);
        response.setName("Rick Sanchez");
        response.setStatus("Alive");
        response.setSpecies("Human");
        response.setType("");
        response.setGender("Male");
        response.setImage("https://rickandmortyapi.com/api/character/avatar/1.jpeg");
        response.setUrl("https://rickandmortyapi.com/api/character/1");
        response.setCreated("2017-11-04T18:48:46.250Z");
        return response;
    }

    private ExternalLocationResponse createMockLocationResponse() {
        ExternalLocationResponse response = new ExternalLocationResponse();
        response.setDimension("Dimension C-137");
        response.setResidents(Arrays.asList(
                "https://rickandmortyapi.com/api/character/38",
                "https://rickandmortyapi.com/api/character/45"
        ));
        return response;
    }


    @Test
    void findOne_returnsTransformedCharacterDTO() {
        int characterId = 1;
        CharacterDTO expectedDTO = new CharacterDTO();

        when(repository.findCharacterById(characterId)).thenReturn(createMockCharacterResponse());
        when(repository.findLocationById(characterId)).thenReturn(createMockLocationResponse());
        when(transformer.transformToCharacterDTO(createMockCharacterResponse(), createMockLocationResponse())).thenReturn(expectedDTO);

        CharacterDTO resultDTO = service.findOne(characterId);

        assertNotNull(resultDTO);
        assertEquals(expectedDTO, resultDTO);

        verify(repository).findCharacterById(characterId);
        verify(repository).findLocationById(characterId);
        verify(transformer).transformToCharacterDTO(createMockCharacterResponse(), createMockLocationResponse());
    }
}