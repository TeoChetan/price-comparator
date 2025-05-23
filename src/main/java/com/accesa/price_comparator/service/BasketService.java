package com.accesa.price_comparator.service;

import com.accesa.price_comparator.model.Product;
import com.accesa.price_comparator.model.dto.BasketDTO;
import com.accesa.price_comparator.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final ProductRepository productRepository;

    public Map<String, List<Product>> optimizeBasket(List<String> productIds) {
        Map<String, List<Product>> storeMap = new HashMap<>();
        for (String id : productIds) {
            productRepository.findByProductId(id).stream()
                    .min(Comparator.comparing(Product::getPrice))
                    .ifPresent(best -> storeMap.computeIfAbsent(best.getStoreName(), k -> new ArrayList<>()).add(best));
        }
        return storeMap;
    }

    public BasketDTO calculateLowestCostAcrossStores(List<String> productIds) {
        List<Product> selectedProducts = new ArrayList<>();
        double total = 0;

        for (String id : productIds) {
            Product cheapest = productRepository.findByProductId(id).stream()
                    .min(Comparator.comparing(Product::getPrice))
                    .orElse(null);
            if (cheapest != null) {
                selectedProducts.add(cheapest);
                total += cheapest.getPrice();
            }
        }

        return new BasketDTO("mixed-stores", total, selectedProducts);
    }
}