package pl.adamklimko.kkp.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import pl.adamklimko.kkp.model.Views;
import pl.adamklimko.kkp.model.products.BoughtProducts;
import pl.adamklimko.kkp.model.rooms.CleanedRooms;

@Data
@Entity
public class AppUser {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @JsonView(Views.Basic.class)
  private String username;

  @JsonView(Views.Internal.class)
  private String password;

  @JsonView(Views.Basic.class)
  @OneToOne
  private Profile profile;

  @JsonView(Views.Extended.class)
  @OneToOne(cascade = CascadeType.ALL)
  private BoughtProducts boughtProducts;

  @JsonView(Views.Extended.class)
  @OneToOne(cascade = CascadeType.ALL)
  private CleanedRooms cleanedRooms;
}
