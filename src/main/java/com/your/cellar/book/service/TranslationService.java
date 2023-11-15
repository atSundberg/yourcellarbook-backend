package com.your.cellar.book.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
@Service
public class TranslationService {

    public Properties fetchProperties(String countryCode) {
        Properties properties = new Properties();
        String filename = "/translations/translation_" + countryCode + ".properties";

        try (InputStream inputStream = new ClassPathResource(filename).getInputStream()) {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            log.error("Error loading translation file: {}", e.getMessage());
        }
        return null;
    }
}
