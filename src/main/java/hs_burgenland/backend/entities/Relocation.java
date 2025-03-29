package hs_burgenland.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "relocations")
public class Relocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int relocationId;
    private String name;
    private Date moveDate;
    private String fromAddress;
    private int fromFloor;
    private boolean fromElevator;
    private String toAddress;
    private int toFloor;
    private boolean toElevator;
    private int numberOfRooms;
    private boolean withPackingService;

    public boolean getFromElevator() {
        return fromElevator;
    }

    public boolean getToElevator() {
        return toElevator;
    }

    public boolean getWithPackingService() {
        return withPackingService;
    }
}