package com.example.RickAndMortyService.dto;

import lombok.Data;

import java.util.List;

@Data
public class CharacterDTO {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private Integer episodeCount;
    private Origin origin;

    @Data
    public static class Origin {
        private String name;
        private String url;
        private String dimension;
        private List<String> residents;
    }
}
