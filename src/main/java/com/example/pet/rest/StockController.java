package com.example.pet.rest;

import com.example.pet.dto.DtoStock;
import com.example.pet.service.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/openinfo/stock")
public class StockController {

    private final StockService stockService;

    @CrossOrigin
    @GetMapping("/list")
    @ApiOperation(value = "Возвращает список всех новостей", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Запрос выполнен успешно"),
            @ApiResponse(code = 400, message = "Неправильный запрос"),
            @ApiResponse(code = 500, message = "Ошибка во время выполнения запроса")
    })
    public ResponseEntity<List<DtoStock>> getAll(@RequestParam(name = "size") Integer size,
                                                 @RequestParam(name = "page") Integer page,
                                                 @RequestParam(name = "sort", required = false) String sort,
                                                 @RequestParam(name = "status", required = false) String status,
                                                 @RequestHeader(name = "oam_remote_user", required = false) Long userId
    ) {
        PageRequest pageRequest;
        if (sort == null) {
            pageRequest = PageRequest.of(page, size);
        } else {
            String type = sort.split(":")[1];
            String name = sort.split(":")[0];
            pageRequest = PageRequest.of(page, size, "ASC".equals(type) ? Sort.Direction.ASC : Sort.Direction.DESC, name);
        }
        List<DtoStock> newsDtos = stockService.getAll(pageRequest);
        return ResponseEntity.ok(newsDtos);
    }
}
