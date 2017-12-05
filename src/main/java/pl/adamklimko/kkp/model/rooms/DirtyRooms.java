package pl.adamklimko.kkp.model.rooms;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dirty_rooms")
public class DirtyRooms extends Rooms {
}
