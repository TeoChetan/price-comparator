package com.accesa.price_comparator.service;

import com.accesa.price_comparator.model.PriceAlert;
import com.accesa.price_comparator.model.dto.TriggeredAlertDTO;
import com.accesa.price_comparator.repository.PriceAlertRepository;
import com.accesa.price_comparator.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {
    private final PriceAlertRepository alertRepository;
    private final ProductRepository productRepository;

    public void saveAlert(PriceAlert alert) {
        alertRepository.save(alert);
    }

    public List<TriggeredAlertDTO> getTriggeredAlerts() {
        List<PriceAlert> allAlerts = alertRepository.findAll();
        List<TriggeredAlertDTO> triggered = new ArrayList<>();

        for (PriceAlert alert : allAlerts) {
            productRepository.findByProductId(alert.getProductId()).stream()
                    .filter(p -> p.getPrice() <= alert.getTargetPrice())
                    .findFirst()
                    .ifPresent(p -> triggered.add(
                            new TriggeredAlertDTO(
                                    p.getProductId(),
                                    p.getProductName(),
                                    p.getBrand(),
                                    p.getPrice(),
                                    alert.getTargetPrice(),
                                    p.getStoreName()
                            )));
        }
        return triggered;
    }
}