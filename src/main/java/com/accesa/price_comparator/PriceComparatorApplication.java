package com.accesa.price_comparator;

import com.accesa.price_comparator.service.CSVDiscountImporter;
import com.accesa.price_comparator.service.CSVProductImporter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class PriceComparatorApplication {
	@Bean
	CommandLineRunner run(CSVProductImporter productImporter, CSVDiscountImporter discountImporter) {
		return args -> {
			productImporter.importAllProductsFromFolder("data/price");
			discountImporter.importAllDiscountsFromFolder("data/discounts");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(PriceComparatorApplication.class, args);
	}

}
