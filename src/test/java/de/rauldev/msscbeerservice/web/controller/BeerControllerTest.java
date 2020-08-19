package de.rauldev.msscbeerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.rauldev.msscbeerservice.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerController.class)
class BeerControllerTest {

    private static final String API_URI = "/api/v1/beer/";


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(API_URI+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveNewBeer() throws Exception {
        //Create Beer Object Instance
        BeerDto beerDto = BeerDto.builder().build();
        String beerToJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post(API_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerToJson))
                        .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = BeerDto.builder().build();
        String beerToJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put(API_URI + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isNoContent());
    }
}