package com.accesa.price_comparator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TriggeredAlertDTO{
       private String productId;
       private String productName;
       private String brand;
       private double currentPrice;
       private double targetPrice;
       private String storeName;
        }
