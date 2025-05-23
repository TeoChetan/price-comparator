package com.accesa.price_comparator.controller;

import com.accesa.price_comparator.model.Product;
import com.accesa.price_comparator.model.dto.SubstitutesDTO;
import com.accesa.price_comparator.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/history/{productId}")
    public List<Product> getPriceHistory(@PathVariable String productId,
                                         @RequestParam(required = false) String store,
                                         @RequestParam(required = false) String category,
                                         @RequestParam(required = false) String brand) {
        return productService.getPriceHistory(productId, store, category, brand);
    }

    @GetMapping("/substitutes/{productId}")
    public List<SubstitutesDTO> getSubstitutes(@PathVariable String productId) {
        return productService.getSubstitutes(productId);
    }

}