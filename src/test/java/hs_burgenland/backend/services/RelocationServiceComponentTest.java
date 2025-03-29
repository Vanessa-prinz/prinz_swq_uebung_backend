package hs_burgenland.backend.services;

import hs_burgenland.backend.entities.Relocation;
import hs_burgenland.backend.repositories.RelocationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class RelocationServiceComponentTest {

    @Autowired
    private RelocationService relocationService;
    @Autowired
    private RelocationRepository relocationRepository;

    private Relocation testRelocation;

    @BeforeEach
    public void setUp() {
        LocalDate testMoveDate = LocalDate.of(2025, 6, 1);

        testRelocation = new Relocation();
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
    @Transactional
    public void testRequestForRelocationSupportInDb() {
        Relocation newRelocation = relocationService.requestForRelocationSupport(
                testRelocation.getName(),
                testRelocation.getMoveDate(),
                testRelocation.getFromAddress(),
                testRelocation.getFromFloor(),
                testRelocation.isFromElevator(),
                testRelocation.getToAddress(),
                testRelocation.getToFloor(),
                testRelocation.isToElevator(),
                testRelocation.getNumberOfRooms(),
                testRelocation.isWithPackingService()
        );

        assertNotNull(newRelocation);

        Optional<Relocation> foundRelocation = relocationRepository.findById(newRelocation.getRelocationId());
        assertTrue(foundRelocation.isPresent());

        Relocation relocationFromDb = foundRelocation.get();
        assertEquals(testRelocation.getName(), relocationFromDb.getName());
        assertEquals(testRelocation.getMoveDate(), relocationFromDb.getMoveDate());
        assertEquals(testRelocation.getFromAddress(), relocationFromDb.getFromAddress());
        assertEquals(testRelocation.getToAddress(), relocationFromDb.getToAddress());
        assertEquals(testRelocation.getFromFloor(), relocationFromDb.getFromFloor());
        assertEquals(testRelocation.getToFloor(), relocationFromDb.getToFloor());
        assertEquals(testRelocation.isFromElevator(), relocationFromDb.isFromElevator());
        assertEquals(testRelocation.isToElevator(), relocationFromDb.isToElevator());
        assertEquals(testRelocation.getNumberOfRooms(), relocationFromDb.getNumberOfRooms());
        assertEquals(testRelocation.isWithPackingService(), relocationFromDb.isWithPackingService());
    }
}