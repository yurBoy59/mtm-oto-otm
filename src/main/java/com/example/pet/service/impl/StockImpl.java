package com.example.pet.service.impl;

import com.example.pet.dto.DtoStock;
import com.example.pet.model.Stock;
import com.example.pet.repo.StockRepository;
import com.example.pet.service.StockService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockImpl implements StockService {

    private final StockRepository stockRepository;

    private ModelMapper modelMapper;

    @PostConstruct
    void init() {
        modelMapper = new ModelMapper();
    }

    @Override
    public DtoStock getById(Long id) {
        Stock stock = stockRepository.findById(id).orElse(null);
        if (stock != null) {
            return modelMapper.map(stock, DtoStock.class);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<DtoStock> getAll(PageRequest pageRequest) {

        return stockRepository
                .findAll(pageRequest)
                .getContent()
                .stream()
                .map(stock -> modelMapper.map(stock, DtoStock.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        stockRepository.deleteById(id);
    }
}
