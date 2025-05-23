package com.accesa.price_comparator.controller;

import com.accesa.price_comparator.model.Product;
import com.accesa.price_comparator.model.dto.BasketDTO;
import com.accesa.price_comparator.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @PostMapping("/optimize")
    public Map<String, List<Product>> optimizeBasket(@RequestBody List<String> productIds) {
        return basketService.optimizeBasket(productIds);
    }

    @PostMapping("/cost")
    public BasketDTO calculateLowestCostAcrossStores(@RequestBody List<String> productIds) {
        return basketService.calculateLowestCostAcrossStores(productIds);
    }
}
