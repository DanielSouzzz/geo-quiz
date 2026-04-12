package com.project.viacep.integration.restcountries;

import com.project.viacep.integration.restcountries.dto.CountryDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/*
* Essa integração servira futuramente para alimentar a IA com informacoes as quais ela devera
* criar perguntas e respostas
* */
@Component
public class RestCountriesClient {

    private final RestClient restClient;

    public RestCountriesClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public CountryDTO[] getCountry() {
        return restClient.get()
                .uri("https://restcountries.com/v3.1/all?fields=name,flags,capital")
                .retrieve()
                .body(CountryDTO[].class);
    }
}