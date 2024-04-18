package com.example.RickAndMortyService.service;

import lombok.Data;

import java.util.List;

@Data
public class ExternalLocationResponse {
    private String dimension;
    private List<String> residents;
}
