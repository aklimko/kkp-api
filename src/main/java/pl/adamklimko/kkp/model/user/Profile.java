package pl.adamklimko.kkp.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;
    private String fullName;
    private String facebookId;
}
