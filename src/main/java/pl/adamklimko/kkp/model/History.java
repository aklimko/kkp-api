package pl.adamklimko.kkp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import pl.adamklimko.kkp.model.user.AppUser;

import javax.persistence.*;
import java.util.Date;

@Entity
public @Data class History {
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @JsonView(Views.Basic.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @JsonView(Views.Basic.class)
    @ManyToOne
    private AppUser user;

    @JsonView(Views.Basic.class)
    @OneToOne(cascade = CascadeType.ALL)
    private ProductsEntry productsEntry;

    @JsonView(Views.Basic.class)
    @OneToOne(cascade = CascadeType.ALL)
    private CleanedRooms roomsEntry;

    @JsonView(Views.Basic.class)
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
}
