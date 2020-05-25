package com.bridgelabz.quantityconversion.controller;

import com.bridgelabz.quantityconversion.dto.ConversionDto;
import com.bridgelabz.quantityconversion.service.IQuantityConversionService;
import com.bridgelabz.quantityconversion.service.Quantity;
import com.bridgelabz.quantityconversion.service.QuantityUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quantity")
@CrossOrigin(origins="http://localhost:3000")
public class QuantityConversionController {

    @Autowired
    private IQuantityConversionService quantityConversionService;

    @GetMapping
    public ResponseEntity getListOfQuantity() {
        return new ResponseEntity(quantityConversionService.getListOfQuantity(), HttpStatus.OK);
    }

    @GetMapping("/{quantityUnits}")
    public ResponseEntity getListOfQuantityUnits(@PathVariable Quantity quantityUnits) {
        return new ResponseEntity(quantityConversionService.getListOfQuantityUnits(quantityUnits), HttpStatus.OK);
    }

    @PostMapping("/conversion/{conversionUnit}")
    public ResponseEntity convertQuantityToUnit(@RequestBody ConversionDto conversionDto, @PathVariable QuantityUnits conversionUnit) {
        return new ResponseEntity(quantityConversionService.convertQuantityToUnit(conversionDto, conversionUnit), HttpStatus.CREATED);
    }

}