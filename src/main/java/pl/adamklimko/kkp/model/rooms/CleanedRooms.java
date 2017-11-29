package pl.adamklimko.kkp.model.rooms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import pl.adamklimko.kkp.model.Views;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public @Data class CleanedRooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @JsonView(Views.Basic.class)
    private int bathroom;

    @JsonView(Views.Basic.class)
    private int kitchen;

    @JsonView(Views.Basic.class)
    private int floor;

    public void addNewCleanedRooms(CleanedRooms cleanedRooms) {
        this.bathroom += cleanedRooms.getBathroom();
        this.kitchen += cleanedRooms.getKitchen();
        this.floor += cleanedRooms.getFloor();
    }
}
