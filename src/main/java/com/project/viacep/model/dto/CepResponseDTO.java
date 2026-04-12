package com.project.viacep.model.dto;

public record CepResponseDTO(String cep, String logradouro, String bairro, String complemento,
                             String localidade, String uf) {
}
