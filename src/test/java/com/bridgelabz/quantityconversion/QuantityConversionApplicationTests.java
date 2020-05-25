package com.bridgelabz.quantityconversion;

import com.bridgelabz.quantityconversion.dto.ConversionDto;
import com.bridgelabz.quantityconversion.service.IQuantityConversionService;
import com.bridgelabz.quantityconversion.service.Quantity;
import com.bridgelabz.quantityconversion.service.QuantityUnits;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class QuantityConversionApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IQuantityConversionService quantityConversionService;

    List<Quantity> quantityList = new ArrayList<>();

    List<QuantityUnits> quantityUnitsList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    public QuantityConversionApplicationTests() {
        this.quantityList.add(Quantity.VOLUME);
        this.quantityList.add(Quantity.LENGTH);
        this.quantityUnitsList.add(QuantityUnits.INCH);
        this.quantityUnitsList.add(QuantityUnits.CM);
        this.quantityUnitsList.add(QuantityUnits.FEET);
    }

    @Test
    void whenQuantity_shouldReturnListOfQuantity() throws Exception {
        when(quantityConversionService.getListOfQuantity()).thenReturn(this.quantityList);
        MvcResult mvcResult = this.mockMvc.perform(get("/quantity")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String quantitiesJson = mvcResult.getResponse().getContentAsString();
        Quantity[] quantities = objectMapper.readValue(quantitiesJson, Quantity[].class);
        System.out.println(quantities[0]);
        Assert.assertEquals(2, quantities.length);
    }

    @Test
    void whenQuantityUnits_shouldReturnListOfQuantityUnits() throws Exception {
        when(quantityConversionService.getListOfQuantityUnits(Quantity.LENGTH)).thenReturn(this.quantityUnitsList);
        MvcResult mvcResult = this.mockMvc.perform(get("/quantity/LENGTH")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String quantitiesJson = mvcResult.getResponse().getContentAsString();
        QuantityUnits[] quantityUnits = objectMapper.readValue(quantitiesJson, QuantityUnits[].class);
        System.out.println(quantityUnits[0]);
        Assert.assertEquals(3, quantityUnits.length);
    }

    @Test
    void whenGiven2Feet_isExpectedInch_shouldReturn24Inch() throws Exception {
        ConversionDto conversionDto = new ConversionDto(2.0, QuantityUnits.FEET);
        String conversionDtoJson = objectMapper.writeValueAsString(conversionDto);
        when(quantityConversionService.convertQuantityToUnit(any(), any()))
                .thenReturn(conversionDto.value);
        MvcResult mvcResult = this.mockMvc.perform(post("/quantity/conversion/INCH")
                .content(conversionDtoJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String quantityResult = mvcResult.getResponse().getContentAsString();
        System.out.println(quantityResult);
        System.out.println(conversionDtoJson);
        Assert.assertEquals(String.valueOf(conversionDto.value), quantityResult);
    }
}
