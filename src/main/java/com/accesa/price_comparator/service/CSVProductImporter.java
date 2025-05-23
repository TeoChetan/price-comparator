package com.accesa.price_comparator.service;

import com.accesa.price_comparator.model.Product;
import com.accesa.price_comparator.repository.ProductRepository;
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
public class CSVProductImporter {
    private final ProductRepository productRepository;



    public void importProducts(String filePath, String storeName, LocalDate date) throws IOException {
        InputStream is = new ClassPathResource(filePath).getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        reader.readLine(); // skip header
        String line;



        while ((line = reader.readLine()) != null) {
            try {
                String[] tokens = line.split(";");
                if (tokens.length < 8) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }

                Product product = new Product(
                        null,
                        tokens[0].trim(),
                        tokens[1].trim(),
                        tokens[2].trim(),
                        tokens[3].trim(),
                        Double.parseDouble(tokens[4].trim()),
                        tokens[5].trim(),
                        Double.parseDouble(tokens[6].trim()),
                        tokens[7].trim(),
                        storeName,
                        date
                );
                boolean exists = productRepository.existsByProductIdAndStoreNameAndDate(
                        product.getProductId(), storeName, date
                );
                if (!exists) {
                    productRepository.save(product);
                }
            } catch (Exception e) {
                System.err.println("Error importing line in " + filePath + ": " + line);
                e.printStackTrace();
            }
        }
    }

    public void importAllProductsFromFolder(String folderPath) throws IOException {
        List<Path> files = FileUtils.listFiles(folderPath);

        for (Path file : files) {
            String fileName = file.getFileName().toString();

            if (!fileName.endsWith(".csv") || fileName.split("_").length != 2) {
                System.out.println("Skipping file: " + fileName);
                continue;
            }

            String[] parts = fileName.replace(".csv", "").split("_");
            String store = parts[0];
            LocalDate date = LocalDate.parse(parts[1]);

            System.out.println("Importing " + fileName + "...");
            importProducts(folderPath + "/" + fileName, store, date);
        }
    }
}
