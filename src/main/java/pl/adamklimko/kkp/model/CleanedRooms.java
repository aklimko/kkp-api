package pl.adamklimko.kkp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public @Data class CleanedRooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    private int bathroom;
    private int kitchen;
    private int floor;

    public void addNewCleanedRooms(CleanedRooms cleanedRooms) {
        this.bathroom += cleanedRooms.getBathroom();
        this.kitchen += cleanedRooms.getKitchen();
        this.floor += cleanedRooms.getFloor();
    }
}
