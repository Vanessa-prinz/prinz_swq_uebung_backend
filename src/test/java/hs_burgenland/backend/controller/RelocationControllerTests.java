package hs_burgenland.backend.controller;

import hs_burgenland.backend.entities.Relocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RelocationControllerTests {

    @Mock
    private RelocationService relocationService;
    @InjectMocks
    private RelocationController relocationController;
    private Relocation testRelocation;

    @BeforeEach
    void setUp() throws Exception {
        testRelocation = new Relocation();
        testRelocation.setRelocationId(12);
        testRelocation.setName("Max Mustermann");
        testRelocation.setMoveDate(new SimpleDateFormat("yyyy-MM-ddd").parse("2025-06-01"));
        testRelocation.setFromAddress("Musterstraße 1 12345 Musterstadt");
        testRelocation.setFromFloor(3);
        testRelocation.setFromElevator(true);
        testRelocation.setToAddress("Hauptstraße 5 54321 Großstadt");
        testRelocation.setToFloor(2);
        testRelocation.setToElevator(false);
        testRelocation.setNumberOfRooms(3);
        testRelocation.setWithPackingService(true);
    }

    @Test
    public void testRequestForRelocationSupport_Success() {
        when(relocationService.requestForRelocationSupport("Max Mustermann", "2025-06-01", "Musterstraße 1 12345 Musterstadt", 3, true, "Hauptstraße 5 54321 Großstadt", 2, false, 3, true)).thenReturn(testRelocation);

        ResponseEntity<?> response = relocationController.requestForRelocationSupport(testRelocation);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(testRelocation, response.getBody());
        verify(relocationService, times(1)).requestForRelocationSupport("Max Mustermann", "2025-06-01", "Musterstraße 1 12345 Musterstadt", 3, true, "Hauptstraße 5 54321 Großstadt", 2, false, 3, true);
    }
}