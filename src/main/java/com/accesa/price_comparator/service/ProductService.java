package com.accesa.price_comparator.service;

import com.accesa.price_comparator.model.Product;
import com.accesa.price_comparator.model.dto.SubstitutesDTO;
import com.accesa.price_comparator.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getPriceHistory(String productId, String store, String category, String brand) {
        return productRepository.findAll().stream()
                .filter(p -> p.getProductId().equals(productId))
                .filter(p -> store == null || p.getStoreName().equalsIgnoreCase(store))
                .filter(p -> category == null || p.getProductCategory().equalsIgnoreCase(category))
                .filter(p -> brand == null || p.getBrand().equalsIgnoreCase(brand))
                .sorted(Comparator.comparing(Product::getDate))
                .toList();
    }

    public List<SubstitutesDTO> getSubstitutes(String productId) {
        Product reference = productRepository.findByProductId(productId)
                .stream()
                .findFirst()
                .orElseThrow();

        return productRepository.findByProductCategory(reference.getProductCategory()).stream()
                .filter(p -> !p.getProductId().equals(productId))
                .map(p -> {
                    double valuePerUnit = p.getPrice() / p.getPackageQuantity();
                    return new SubstitutesDTO(
                            p.getProductId(),
                            p.getProductName(),
                            p.getBrand(),
                            p.getPrice(),
                            p.getPackageQuantity(),
                            p.getPackageUnit(),
                            p.getStoreName(),
                            valuePerUnit
                    );
                })
                .sorted(Comparator.comparing(SubstitutesDTO::getValuePerUnit))
                .limit(10)
                .toList();
    }

}