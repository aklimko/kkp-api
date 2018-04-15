package pl.adamklimko.kkp.model.rooms;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rooms_entry")
public class RoomsEntry extends Rooms {

  public RoomsEntry() {
  }

  public RoomsEntry(Rooms rooms) {
    super(rooms);
  }
}
