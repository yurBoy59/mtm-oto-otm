package com.example.pet.service;

import com.example.pet.dto.DtoCategory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    DtoCategory getById(Long id);

    List<DtoCategory> getAll(PageRequest pageRequest, Long id);

    void delete( Long id);
}
