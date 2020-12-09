package com.example.pet.service;

import com.example.pet.dto.DtoStock;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockService {

    DtoStock getById(Long id);

    List<DtoStock> getAll(PageRequest pageRequest);

    void delete(Long id);
}
