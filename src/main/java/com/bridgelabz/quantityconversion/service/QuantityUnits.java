package com.bridgelabz.quantityconversion.service;

import static com.bridgelabz.quantityconversion.service.Quantity.*;

public enum QuantityUnits {

    FEET(12.0, LENGTH), INCH(1.0, LENGTH),
    YARD(36.0, LENGTH), CM(0.4, LENGTH),

    ML(0.001, VOLUME), LITRE(1, VOLUME),
    GALLON(3.78, VOLUME),

    GRAM(0.001, WEIGHT), KG(1.0, WEIGHT),
    TONNE(1000.0, WEIGHT),

    FAHRENHEIT(1.0, TEMPERATURE), CELSIUS(1.0,TEMPERATURE);

    double baseUnitConversion;
    Quantity quantity;

    QuantityUnits(double baseUnitConversion, Quantity quantity) {
        this.baseUnitConversion = baseUnitConversion;
        this.quantity = quantity;
    }
}
