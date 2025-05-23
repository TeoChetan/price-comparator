package com.accesa.price_comparator.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.List;

public class FileUtils {

    public static List<Path> listFiles(String folder) throws IOException {
        try {
            URI uri = new ClassPathResource(folder).getURI();
            Path path = Paths.get(uri);
            return Files.walk(path)
                    .filter(Files::isRegularFile)
                    .toList();
        } catch (Exception e) {
            throw new IOException("Could not load files from: " + folder, e);
        }
    }
}
