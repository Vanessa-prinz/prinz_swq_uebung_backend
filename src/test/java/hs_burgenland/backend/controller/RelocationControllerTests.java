package hs_burgenland.backend.controller;

import hs_burgenland.backend.entities.Relocation;
import hs_burgenland.backend.services.RelocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RelocationControllerTests {

    @Mock
    private RelocationService relocationService;
    @InjectMocks
    private RelocationController relocationController;
    private Relocation testRelocation;
    private LocalDate testMoveDate;

    @BeforeEach
    void setUp() {
        testMoveDate = LocalDate.of(2025, 6, 1);

        testRelocation = new Relocation();
        testRelocation.setRelocationId(12);
        testRelocation.setName("Max Mustermann");
        testRelocation.setMoveDate(testMoveDate);
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
        when(relocationService.requestForRelocationSupport("Max Mustermann", testMoveDate, "Musterstraße 1 12345 Musterstadt",
                3, true, "Hauptstraße 5 54321 Großstadt", 2, false,
                3, true)).thenReturn(testRelocation);

        ResponseEntity<?> response = relocationController.requestForRelocationSupport(testRelocation);

        assertEquals(201, response.getStatusCode().value());
        assertEquals(testRelocation, response.getBody());
        verify(relocationService, times(1)).requestForRelocationSupport("Max Mustermann", testMoveDate,
                "Musterstraße 1 12345 Musterstadt", 3, true, "Hauptstraße 5 54321 Großstadt",
                2, false, 3, true);
    }
}