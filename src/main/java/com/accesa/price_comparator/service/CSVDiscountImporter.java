package com.accesa.price_comparator.service;

import com.accesa.price_comparator.model.Discount;
import com.accesa.price_comparator.repository.DiscountRepository;
import com.accesa.price_comparator.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CSVDiscountImporter {
    private final DiscountRepository discountRepository;

    public void importDiscounts(String filePath, String storeName) throws IOException {
        InputStream is = new ClassPathResource(filePath).getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        reader.readLine(); // skip header
        String line;

        while ((line = reader.readLine()) != null) {
            try {
                String[] tokens = line.split(";");
                if (tokens.length < 9) {
                    System.err.println("Skipping malformed discount line: " + line);
                    continue;
                }

                Discount discount = new Discount(
                        null,
                        tokens[0].trim(),
                        tokens[1].trim(),
                        tokens[2].trim(),
                        Double.parseDouble(tokens[3].trim()),
                        tokens[4].trim(),
                        tokens[5].trim(),
                        LocalDate.parse(tokens[6].trim()),
                        LocalDate.parse(tokens[7].trim()),
                        Integer.parseInt(tokens[8].trim()),
                        storeName
                );
                boolean exists = discountRepository.existsByProductIdAndStoreName(
                        discount.getProductId(), storeName
                );
                if (!exists) {
                    discountRepository.save(discount);
                }
            } catch (Exception e) {
                System.err.println("Error importing line in file " + filePath + ": " + line);
                e.printStackTrace();
            }
        }
    }

    public void importAllDiscountsFromFolder(String folderPath) throws IOException {
        List<Path> files = FileUtils.listFiles(folderPath);

        for (Path file : files) {
            String fileName = file.getFileName().toString();

            if (!fileName.endsWith(".csv") || fileName.split("_").length < 3) {
                System.out.println("Skipping file: " + fileName);
                continue;
            }

            String[] parts = fileName.replace(".csv", "").split("_");
            String store = parts[0];

            System.out.println("Importing discount file: " + fileName);
            importDiscounts(folderPath + "/" + fileName, store);
        }
    }
}
