package com.accesa.price_comparator.repository;

import com.accesa.price_comparator.model.PriceAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceAlertRepository extends JpaRepository<PriceAlert, Long> { }

