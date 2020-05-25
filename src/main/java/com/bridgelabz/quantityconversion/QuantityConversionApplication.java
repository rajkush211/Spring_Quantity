package com.bridgelabz.quantityconversion;

import com.bridgelabz.quantityconversion.dto.ConversionDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class QuantityConversionApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(QuantityConversionApplication.class, args);

    }

}
