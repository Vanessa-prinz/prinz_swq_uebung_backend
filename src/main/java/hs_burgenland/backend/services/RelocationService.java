package hs_burgenland.backend.services;

import hs_burgenland.backend.entities.Relocation;
import hs_burgenland.backend.repositories.RelocationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RelocationService {

    private final RelocationRepository relocationRepository;

    public RelocationService(RelocationRepository relocationRepository) {
        this.relocationRepository = relocationRepository;
    }

    public Relocation requestForRelocationSupport(String name, LocalDateTime moveDate, String fromAddress, int fromFloor,
                                                  boolean fromElevator, String toAddress, int toFloor, boolean toElevator,
                                                  int numberOfRooms, boolean withPackingService) {
        Relocation relocation = new Relocation();
        relocation.setName(name);
        relocation.setMoveDate(moveDate);
        relocation.setFromAddress(fromAddress);
        relocation.setFromFloor(fromFloor);
        relocation.setFromElevator(fromElevator);
        relocation.setToAddress(toAddress);
        relocation.setToFloor(toFloor);
        relocation.setToElevator(toElevator);
        relocation.setNumberOfRooms(numberOfRooms);
        relocation.setWithPackingService(withPackingService);

        return relocationRepository.save(relocation);
    }
}