package com.example.RickAndMortyService.service;

import com.example.RickAndMortyService.dto.CharacterDTO;
import org.springframework.stereotype.Service;

@Service
public class CharacterDataTransformer {

    public CharacterDTO transformToCharacterDTO(ExternalCharacterResponse externalResponse) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(externalResponse.getId());
        dto.setName(externalResponse.getName());
        dto.setStatus(externalResponse.getStatus());
        dto.setSpecies(externalResponse.getSpecies());
        dto.setType(externalResponse.getType());
        dto.setEpisodeCount(externalResponse.getEpisode().size());

        CharacterDTO.Origin origin = new CharacterDTO.Origin();
        origin.setName(externalResponse.getOrigin().getName());
        origin.setUrl(externalResponse.getOrigin().getUrl());
        origin.setDimension(externalResponse.getLocation().getDimension());
        origin.setResidents(externalResponse.getLocation().getResidents());

        dto.setOrigin(origin);
        return dto;
    }
}
