package com.bridgelabz.quantityconversion.service;

import com.bridgelabz.quantityconversion.dto.ConversionDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.bridgelabz.quantityconversion.service.Quantity.*;

@Service
public class QuantityConversionService {

    public List<Quantity> getListOfQuantity() {
        return Arrays.stream(Quantity.values()).collect(Collectors.toList());
    }

    public List<QuantityUnits> getListOfQuantityUnits(Quantity quantity) {
        return Arrays.stream(QuantityUnits.values()).filter(qUnit -> qUnit.quantity.equals(quantity)).collect(Collectors.toList());
    }

    public ConversionDto convertQuantityToUnit( ConversionDto conversionDto, QuantityUnits quantityUnits) {

        return new ConversionDto(conversionDto.value * conversionDto.quantityUnits.baseUnitConversion, quantityUnits);

    }
}
