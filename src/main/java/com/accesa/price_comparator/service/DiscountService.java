package com.accesa.price_comparator.service;

import com.accesa.price_comparator.model.Discount;
import com.accesa.price_comparator.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;

    public List<Discount> getBestDiscounts(LocalDate date) {
        LocalDate today = (date != null) ? date : LocalDate.now();
        return discountRepository.findByDateRange(today)
                .stream()
                .sorted(Comparator.comparingInt(Discount::getPercentageOfDiscount).reversed())
                .limit(10)
                .toList();
    }

    public List<Discount> getNewDiscounts(LocalDate date) {
        LocalDate today = (date != null) ? date : LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        return discountRepository.findByFromDateBetween(yesterday, today);
    }
}