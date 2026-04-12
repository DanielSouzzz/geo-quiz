package com.project.viacep.integration.restcountries.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryNameDTO(String common) {}