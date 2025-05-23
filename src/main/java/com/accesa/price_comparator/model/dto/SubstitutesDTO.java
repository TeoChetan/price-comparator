package com.accesa.price_comparator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubstitutesDTO {
    private String productId;
    private String productName;
    private String brand;
    private double price;
    private double packageQuantity;
    private String packageUnit;
    private String storeName;
    private double valuePerUnit;
}

