package com.project.viacep.integration.restcountries.dto;

import java.util.List;

public record CountryDTO(
        CountryFlagDTO flags,
        CountryNameDTO name,
        List<String> capital
){
    public String getCapitalName() {
        return !capital.isEmpty() ? capital.get(0) : null;
    }
}