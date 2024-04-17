package com.example.RickAndMortyService.controller;

import com.example.RickAndMortyService.dto.CharacterDTO;
import com.example.RickAndMortyService.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
public class CharacterController {

    public final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{id}")
    public CharacterDTO getCharacter(@PathVariable int id){
        return this.characterService.findOne(id);
    }
}
