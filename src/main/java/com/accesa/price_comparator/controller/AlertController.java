package com.accesa.price_comparator.controller;

import com.accesa.price_comparator.model.PriceAlert;
import com.accesa.price_comparator.model.dto.TriggeredAlertDTO;
import com.accesa.price_comparator.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {
    private final AlertService alertService;

    @PostMapping
    public ResponseEntity<String> createAlert(@RequestBody PriceAlert alert) {
        alertService.saveAlert(alert);
        return ResponseEntity.ok("Alert saved.");
    }

    @GetMapping("/triggered")
    public List<TriggeredAlertDTO> getTriggeredAlerts() {
        return alertService.getTriggeredAlerts();
    }
}