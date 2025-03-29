package hs_burgenland.backend.controller;

import hs_burgenland.backend.entities.Relocation;
import hs_burgenland.backend.services.RelocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelocationController {

    @Autowired
    private RelocationService relocationService;

    @PostMapping("/relocation")
    public ResponseEntity<?> requestForRelocationSupport(@RequestBody Relocation relocation) {
        try {
            Relocation createdRelocation = relocationService.requestForRelocationSupport(
                    relocation.getName(),
                    relocation.getMoveDate(),
                    relocation.getFromAddress(),
                    relocation.getFromFloor(),
                    relocation.isFromElevator(),
                    relocation.getToAddress(),
                    relocation.getToFloor(),
                    relocation.isToElevator(),
                    relocation.getNumberOfRooms(),
                    relocation.isWithPackingService());
            return ResponseEntity.status(201).body(createdRelocation);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Server can not handle request.");
        }
    }

    @GetMapping("/relocations")
    public ResponseEntity<?> getAllRelocations() {
        try {
            return ResponseEntity.status(200).body(relocationService.getAllRelocations());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Server can not handle request.");
        }
    }
}