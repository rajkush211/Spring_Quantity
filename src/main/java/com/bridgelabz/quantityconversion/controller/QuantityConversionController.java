package com.bridgelabz.quantityconversion.controller;

import com.bridgelabz.quantityconversion.dto.ConversionDto;
import com.bridgelabz.quantityconversion.service.Quantity;
import com.bridgelabz.quantityconversion.service.QuantityConversionService;
import com.bridgelabz.quantityconversion.service.QuantityUnits;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuantityConversionController {

    @Autowired
    QuantityConversionService quantityConversionService;

//    @GetMapping("/quantity")
//    public List<Quantity> getListOfQuantity() {
//        return quantityConversionService.getListOfQuantity();
//    }

    @GetMapping("/quantity")
    public ResponseEntity getListOfQuantity() {
        return new ResponseEntity(quantityConversionService.getListOfQuantity(), HttpStatus.OK);
    }

    @GetMapping("/quantity/{quantityUnits}")
    public ResponseEntity getListOfQuantityUnits(@PathVariable Quantity quantityUnits) {
        return new ResponseEntity(quantityConversionService.getListOfQuantityUnits(quantityUnits), HttpStatus.OK);
    }

    @PostMapping("/quantity/conversion/{conversionUnit}")
    public ResponseEntity convertQuantityToUnit(@RequestBody ConversionDto conversionDto, @PathVariable QuantityUnits conversionUnit) {
        return new ResponseEntity(quantityConversionService.convertQuantityToUnit(conversionDto, conversionUnit), HttpStatus.OK);
    }

}