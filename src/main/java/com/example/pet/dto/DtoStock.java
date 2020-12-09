package com.example.pet.dto;

import lombok.Data;

import java.util.Set;

@Data
public class DtoStock {

    private Long id;

    private String stockCode;

    private String stockName;

    private Set<DtoCategory> categories;
}
