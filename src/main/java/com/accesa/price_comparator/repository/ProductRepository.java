package com.accesa.price_comparator.repository;

import com.accesa.price_comparator.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByStoreNameAndDate(String storeName, LocalDate date);
    List<Product> findByProductId(String productId);
    List<Product> findByProductCategory(String productCategory);
    boolean existsByProductIdAndStoreNameAndDate(String productId, String storeName, LocalDate date);


}

