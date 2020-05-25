package com.bridgelabz.quantityconversion.service;

import com.bridgelabz.quantityconversion.dto.ConversionDto;

import java.util.List;

public interface IQuantityConversionService {
    public List<Quantity> getListOfQuantity();
    public List<QuantityUnits> getListOfQuantityUnits(Quantity quantity);
    public Double convertQuantityToUnit(ConversionDto conversionDto, QuantityUnits quantityUnits);
}
