package pl.adamklimko.kkp.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import pl.adamklimko.kkp.model.Views;

@Entity
public @Data
class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  private long id;

  @JsonView(Views.Basic.class)
  private String fullName;

  @JsonView(Views.Basic.class)
  private String facebookId;
}
