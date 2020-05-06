package com.bridgelabz.quantityconversion.dto;

import com.bridgelabz.quantityconversion.service.Quantity;
import com.bridgelabz.quantityconversion.service.QuantityUnits;

public class ConversionDto {
    public Double value;
    public QuantityUnits quantityUnits;

    public ConversionDto(Double value, QuantityUnits quantityUnits) {
        this.value = value;
        this.quantityUnits = quantityUnits;
    }
}
