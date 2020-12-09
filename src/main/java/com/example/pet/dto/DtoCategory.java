package com.example.pet.dto;

import lombok.Data;

import java.util.Set;

@Data
public class DtoCategory {

    private Long id;

    private String name;

    private String stockName;

    private Set<DtoStock> stocks;
}
