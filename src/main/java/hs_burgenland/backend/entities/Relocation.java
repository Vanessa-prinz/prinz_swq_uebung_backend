package hs_burgenland.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "relocations")
public class Relocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int relocationId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime moveDate;
    private String fromAddress;
    private int fromFloor;
    private boolean fromElevator;
    private String toAddress;
    private int toFloor;
    private boolean toElevator;
    private int numberOfRooms;
    private boolean withPackingService;

    public int getRelocationId() {
        return relocationId;
    }

    public void setRelocationId(int relocationId) {
        this.relocationId = relocationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getMoveDate() {
        return moveDate;
    }

    public void setMoveDate(LocalDateTime moveDate) {
        this.moveDate = moveDate;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public int getFromFloor() {
        return fromFloor;
    }

    public void setFromFloor(int fromFloor) {
        this.fromFloor = fromFloor;
    }

    public boolean isFromElevator() {
        return fromElevator;
    }

    public void setFromElevator(boolean fromElevator) {
        this.fromElevator = fromElevator;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public int getToFloor() {
        return toFloor;
    }

    public void setToFloor(int toFloor) {
        this.toFloor = toFloor;
    }

    public boolean isToElevator() {
        return toElevator;
    }

    public void setToElevator(boolean toElevator) {
        this.toElevator = toElevator;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public boolean isWithPackingService() {
        return withPackingService;
    }

    public void setWithPackingService(boolean withPackingService) {
        this.withPackingService = withPackingService;
    }
}