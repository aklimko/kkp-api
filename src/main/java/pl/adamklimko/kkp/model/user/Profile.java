package pl.adamklimko.kkp.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import pl.adamklimko.kkp.model.Views;

import javax.persistence.*;

@Entity
public @Data class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    @JsonView(Views.Basic.class)
    private String fullName;

    @JsonView(Views.Basic.class)
    private String facebookId;
}
