package hs_burgenland.backend.services;

import hs_burgenland.backend.entities.Relocation;
import hs_burgenland.backend.repositories.RelocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RelocationServiceTests {

    @Mock
    private RelocationRepository relocationRepository;
    @InjectMocks
    private RelocationService relocationService;
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
        when(relocationRepository.save(any(Relocation.class))).thenReturn(testRelocation);

        Relocation createdRelocation = relocationService.requestForRelocationSupport(testRelocation.getName(), testRelocation.getMoveDate(), testRelocation.getFromAddress(), testRelocation.getFromFloor(), testRelocation.getFromElevator(), testRelocation.getToAddress(), testRelocation.getToFloor(), testRelocation.getToElevator(), testRelocation.getNumberOfRooms(), testRelocation.getWithPackingService());

        assertNotNull(createdRelocation);
        assertEquals(12, createdRelocation.getRelocationId());
        assertEquals("Max Mustermann", createdRelocation.getName());
        assertEquals("2025-06-01", createdRelocation.getMoveDate());
        assertEquals("Musterstraße 1 12345 Musterstadt", createdRelocation.getFromAddress());
        assertEquals(3, createdRelocation.getFromFloor());
        assertTrue(createdRelocation.getFromElevator());
        assertEquals("Hauptstraße 5 54321 Großstadt", createdRelocation.getToAddress());
        assertEquals(2, createdRelocation.getToFloor());
        assertFalse(createdRelocation.getToElevator());
        assertEquals(3, createdRelocation.getNumberOfRooms());
        assertTrue(createdRelocation.getWithPackingService());
        verify(relocationRepository, times(1)).save(any(Relocation.class));
    }
}