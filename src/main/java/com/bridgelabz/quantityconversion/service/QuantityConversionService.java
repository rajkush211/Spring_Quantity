package com.bridgelabz.quantityconversion.service;

import com.bridgelabz.quantityconversion.dto.ConversionDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.bridgelabz.quantityconversion.service.Quantity.*;

@Service
public class QuantityConversionService implements IQuantityConversionService {

    public List<Quantity> getListOfQuantity() {
        return Arrays.stream(Quantity.values()).collect(Collectors.toList());
    }

    public List<QuantityUnits> getListOfQuantityUnits(Quantity quantity) {
        return Arrays.stream(QuantityUnits.values()).filter(qUnit -> qUnit.quantity.equals(quantity)).collect(Collectors.toList());
    }

    private static double farenheitToCelcius(double fahrenheit) {
        return ((fahrenheit - 32) * (5.0 / 9.0));
    }

    private static double celciusToFarenheit(double celcius) {
        return ((celcius * (9.0 / 5.0)) + 32);
    }

    public Double convertQuantityToUnit(ConversionDto conversionDto, QuantityUnits quantityUnits) {
        try {
            if(conversionDto.quantityUnits.quantity.equals(TEMPERATURE)) {
                if(conversionDto.quantityUnits.equals(QuantityUnits.FAHRENHEIT) && quantityUnits.equals(QuantityUnits.CELSIUS)) {
                    return farenheitToCelcius(conversionDto.value);
                }
                else if(conversionDto.quantityUnits.equals(QuantityUnits.CELSIUS) && quantityUnits.equals(QuantityUnits.FAHRENHEIT)) {
                    return celciusToFarenheit(conversionDto.value);
                }
                else {
                    return conversionDto.value;
                }
            }
            double result = (conversionDto.value * conversionDto.quantityUnits.baseUnitConversion) / quantityUnits.baseUnitConversion;
            return result;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
