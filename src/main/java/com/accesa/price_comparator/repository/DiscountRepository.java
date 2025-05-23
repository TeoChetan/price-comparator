package com.accesa.price_comparator.repository;


import com.accesa.price_comparator.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, String> {
    List<Discount> findByToDateAfter(LocalDate date);

    @Query("SELECT d FROM Discount d WHERE :today BETWEEN d.fromDate AND d.toDate")
    List<Discount> findByDateRange(@Param("today") LocalDate today);

    List<Discount> findByFromDateBetween(LocalDate from, LocalDate to);
    boolean existsByProductIdAndStoreName(String productId, String storeName);


}
