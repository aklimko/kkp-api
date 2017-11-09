package pl.adamklimko.kkp.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.adamklimko.kkp.model.BoughtProducts;
import pl.adamklimko.kkp.model.CleanedRooms;

import javax.persistence.*;

@Entity
public @Data class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;

    @JsonIgnore
    @OneToOne
    private Profile profile;

    @JsonIgnore
    @OneToOne
    private BoughtProducts boughtProducts;

    @JsonIgnore
    @OneToOne
    private CleanedRooms cleanedRooms;
}