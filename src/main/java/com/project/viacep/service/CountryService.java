package com.project.viacep.service;

import com.project.viacep.integration.restcountries.RestCountriesClient;
import com.project.viacep.integration.restcountries.dto.CountryDTO;
import com.project.viacep.model.Country;
import com.project.viacep.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final RestCountriesClient restCountriesClient;

    private final CountryRepository countryRepository;

    public CountryService(RestCountriesClient restCountriesClient, CountryRepository countryRepository) {
        this.restCountriesClient = restCountriesClient;
        this.countryRepository = countryRepository;
    }

    public void loadCountry() {
        List<CountryDTO> countryDTOList = List.of(restCountriesClient.getCountry());

        for (int i = 0; i < countryDTOList.size(); i++) {
            Country country = Country.builder()
                    .name(countryDTOList.get(i).name().common())
                    .capital(countryDTOList.get(i).getCapitalName())
                    .flagUrl(countryDTOList.get(i).flags().svg())
                    .build();

            countryRepository.save(country);
        }
    }
}
