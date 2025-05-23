package com.accesa.price_comparator.controller;

import com.accesa.price_comparator.model.Discount;
import com.accesa.price_comparator.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

// DiscountController.java
@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping("/best")
    public List<Discount> getBestDiscounts(@RequestParam(required = false)
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                           LocalDate date) {
        return discountService.getBestDiscounts(date);
    }

    @GetMapping("/new")
    public List<Discount> getNewDiscounts(@RequestParam(required = false)
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                          LocalDate date) {
        return discountService.getNewDiscounts(date);
    }
}