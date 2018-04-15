package pl.adamklimko.kkp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import pl.adamklimko.kkp.model.products.ProductsEntry;
import pl.adamklimko.kkp.model.rooms.RoomsEntry;
import pl.adamklimko.kkp.model.user.AppUser;

@Data
@Builder
@Entity
public class History {

  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long id;

  @JsonView(Views.Basic.class)
  @JsonFormat(pattern = "dd-MM-YYYY HH:mm")
  private LocalDateTime time;

  @JsonView(Views.Basic.class)
  @ManyToOne
  private AppUser user;

  @JsonView(Views.Basic.class)
  @OneToOne(cascade = CascadeType.ALL)
  private ProductsEntry productsEntry;

  @JsonView(Views.Basic.class)
  @OneToOne(cascade = CascadeType.ALL)
  private RoomsEntry roomsEntry;

  @JsonView(Views.Basic.class)
  @Enumerated(EnumType.STRING)
  private ActionType actionType;
}
