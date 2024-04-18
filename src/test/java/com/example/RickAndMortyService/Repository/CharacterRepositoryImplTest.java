package com.example.RickAndMortyService.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import com.example.RickAndMortyService.service.ExternalLocationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.RickAndMortyService.service.ExternalCharacterResponse;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class CharacterRepositoryImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CharacterRepositoryImpl repository;

    @Test
    void findCharacterById_ShouldReturnCharacter_WhenApiResponseIsSuccessful() {
        int characterId = 1;

        // mock character response
        ExternalCharacterResponse expectedResponse = new ExternalCharacterResponse();
        expectedResponse.setId(characterId);
        expectedResponse.setName("Rick Sanchez");

        when(restTemplate.exchange(
                any(String.class),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class))
        ).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        ExternalCharacterResponse actualResponse = repository.findCharacterById(characterId);

        // mock location response
        ExternalLocationResponse expectedLocationResponse = new ExternalLocationResponse();
        expectedLocationResponse.setDimension("Dimension C-137");
        expectedLocationResponse.setResidents(Arrays.asList("Location 1", "Location 2"));

        when(restTemplate.exchange(
                any(String.class),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class))
        ).thenReturn(new ResponseEntity<>(expectedLocationResponse, HttpStatus.OK));

        ExternalLocationResponse actualLocationResponse = repository.findLocationById(characterId);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getName(), actualResponse.getName());

        assertNotNull(actualLocationResponse);
        assertEquals(actualLocationResponse.getDimension(), actualLocationResponse.getDimension());
        assertEquals(actualLocationResponse.getResidents(), actualLocationResponse.getResidents());
    }


    @Test
    void findCharacterById_ShouldThrowRuntimeException_WhenApiResponseIsNotSuccessful() {
        int characterId = 1;
        when(restTemplate.exchange(
                any(String.class),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class))
        ).thenReturn(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(RuntimeException.class, () -> repository.findCharacterById(characterId));
    }
}