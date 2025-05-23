package com.accesa.price_comparator.model.dto;

import com.accesa.price_comparator.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BasketDTO {
   private String storeName;
   private double totalCost;
   private List<Product> products;
}
