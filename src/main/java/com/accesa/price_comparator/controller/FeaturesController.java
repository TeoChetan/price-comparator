//package com.accesa.price_comparator.controller;
//
//import com.accesa.price_comparator.model.Discount;
//import com.accesa.price_comparator.model.PriceAlert;
//import com.accesa.price_comparator.model.Product;
//import com.accesa.price_comparator.repository.DiscountRepository;
//import com.accesa.price_comparator.repository.PriceAlertRepository;
//import com.accesa.price_comparator.repository.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.*;
//
//@RestController
//@RequestMapping("/api")
//@RequiredArgsConstructor
//public class FeaturesController {
//
//    private final DiscountRepository discountRepository;
//    private final ProductRepository productRepository;
//    private final PriceAlertRepository alertRepository;
//
//    // 1. Best Discounts
//    @GetMapping("/discounts/best")
//    public List<Discount> getBestDiscounts(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        LocalDate today = (date != null) ? date : LocalDate.now();
//        return discountRepository.findByDateRange(today)
//                .stream()
//                .sorted(Comparator.comparingInt(Discount::getPercentageOfDiscount).reversed())
//                .limit(10)
//                .toList();
//    }
//
//
//    @GetMapping("/discounts/new")
//    public List<Discount> getNewDiscounts(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        LocalDate today = (date != null) ? date : LocalDate.now();
//        LocalDate yesterday = today.minusDays(3);
//        return discountRepository.findByFromDateBetween(yesterday, today);
//    }
//
//
//    // 3. Price History
//    @GetMapping("/products/history/{productId}")
//    public List<Product> getPriceHistory(@PathVariable String productId,
//                                         @RequestParam(required = false) String store,
//                                         @RequestParam(required = false) String category,
//                                         @RequestParam(required = false) String brand) {
//        return productRepository.findAll().stream()
//                .filter(p -> p.getProductId().equals(productId))
//                .filter(p -> store == null || p.getStoreName().equalsIgnoreCase(store))
//                .filter(p -> category == null || p.getProductCategory().equalsIgnoreCase(category))
//                .filter(p -> brand == null || p.getBrand().equalsIgnoreCase(brand))
//                .sorted(Comparator.comparing(Product::getDate))
//                .toList();
//    }
//
//    // 4. Substitutes (value per unit)
//    @GetMapping("/products/substitutes/{productId}")
//    public List<Product> getSubstitutes(@PathVariable String productId) {
//        Product reference = productRepository.findById(productId).orElseThrow();
//        return productRepository.findByProductCategory(reference.getProductCategory()).stream()
//                .filter(p -> !p.getProductId().equals(productId))
//                .map(p -> {
//                    double valuePerUnit = p.getPrice() / p.getPackageQuantity();
//                    p.setPrice(valuePerUnit); // override price with value per unit for simplicity
//                    return p;
//                })
//                .sorted(Comparator.comparing(Product::getPrice))
//                .limit(10)
//                .toList();
//    }
//
//    // 5. Shopping Basket Optimizer
//    @PostMapping("/basket/optimize")
//    public Map<String, List<Product>> optimizeBasket(@RequestBody List<String> productIds) {
//        Map<String, List<Product>> storeMap = new HashMap<>();
//        for (String id : productIds) {
//            productRepository.findByProductId(id).stream()
//                    .min(Comparator.comparing(Product::getPrice))
//                    .ifPresent(best -> storeMap.computeIfAbsent(best.getStoreName(), k -> new ArrayList<>()).add(best));
//        }
//        return storeMap;
//    }
//
//    // 6. Set Price Alert
//    @PostMapping("/alerts")
//    public ResponseEntity<String> createAlert(@RequestBody PriceAlert alert) {
//        alertRepository.save(alert);
//        return ResponseEntity.ok("Alert saved.");
//    }
//
//    // 6b. Check Triggered Alerts
//    @GetMapping("/alerts/triggered")
//    public List<PriceAlert> getTriggeredAlerts() {
//        List<PriceAlert> allAlerts = alertRepository.findAll();
//        List<PriceAlert> triggered = new ArrayList<>();
//
//        for (PriceAlert alert : allAlerts) {
//            productRepository.findByProductId(alert.getProductId()).stream()
//                    .filter(p -> p.getPrice() <= alert.getTargetPrice())
//                    .findFirst()
//                    .ifPresent(p -> triggered.add(alert));
//        }
//        return triggered;
//    }
//}
