package hs_burgenland.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hs_burgenland.backend.entities.Relocation;
import hs_burgenland.backend.repositories.RelocationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RelocationControllerApiTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RelocationRepository relocationRepository;

    @BeforeEach
    public void setUp() {
        relocationRepository.deleteAll();
    }

    @Transactional
    @Test
    public void testRequestForRelocationSupportApi() throws Exception {
        Relocation newRelocation = getNewRelocation();

        mockMvc.perform(post("/relocation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRelocation)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.relocationId").exists())
                .andExpect(jsonPath("$.name").value("Max Mustermann"))
                .andExpect(jsonPath("$.moveDate").value("2025-06-01"))
                .andExpect(jsonPath("$.fromAddress").value("Musterstraße 1 12345 Musterstadt"))
                .andExpect(jsonPath("$.fromFloor").value(3))
                .andExpect(jsonPath("$.fromElevator").value(true))
                .andExpect(jsonPath("$.toAddress").value("Hauptstraße 5 54321 Großstadt"))
                .andExpect(jsonPath("$.toFloor").value(2))
                .andExpect(jsonPath("$.toElevator").value(false))
                .andExpect(jsonPath("$.numberOfRooms").value(3))
                .andExpect(jsonPath("$.withPackingService").value(true));
    }

    private static Relocation getNewRelocation() {
        Relocation newRelocation = new Relocation();
        newRelocation.setName("Max Mustermann");
        LocalDate testMoveDate = LocalDate.of(2025, 6, 1);
        newRelocation.setMoveDate(testMoveDate);
        newRelocation.setFromAddress("Musterstraße 1 12345 Musterstadt");
        newRelocation.setFromFloor(3);
        newRelocation.setFromElevator(true);
        newRelocation.setToAddress("Hauptstraße 5 54321 Großstadt");
        newRelocation.setToFloor(2);
        newRelocation.setToElevator(false);
        newRelocation.setNumberOfRooms(3);
        newRelocation.setWithPackingService(true);
        return newRelocation;
    }
}