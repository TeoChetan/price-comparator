package com.accesa.price_comparator.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "discounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;

    private String productName;
    private String brand;
    private double packageQuantity;
    private String packageUnit;
    private String productCategory;

    private LocalDate fromDate;
    private LocalDate toDate;
    private int percentageOfDiscount;

    private String storeName;
}
