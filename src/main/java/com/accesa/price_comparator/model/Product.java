package com.accesa.price_comparator.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private String productName;
    private String productCategory;
    private String brand;

    private double packageQuantity;
    private String packageUnit;
    private double price;
    private String currency;

    private String storeName;
    private LocalDate date;
}
