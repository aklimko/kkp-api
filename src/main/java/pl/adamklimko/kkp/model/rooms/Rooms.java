package pl.adamklimko.kkp.model.rooms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import pl.adamklimko.kkp.model.Views;

@Data
@MappedSuperclass
public class Rooms {

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

  public Rooms() {
  }

  public Rooms(Rooms rooms) {
    BeanUtils.copyProperties(rooms, this);
  }

  public void addNewCleanedRooms(Rooms cleanedRooms) {
    this.bathroom += cleanedRooms.bathroom;
    this.kitchen += cleanedRooms.kitchen;
    this.floor += cleanedRooms.floor;
  }

  public void addNewDirtyRooms(Rooms dirtyRooms) {
    setFieldsToValueWhenMoreThanZero(dirtyRooms, 1);
  }

  public void removeDirtyRooms(Rooms cleanedRooms) {
    setFieldsToValueWhenMoreThanZero(cleanedRooms, 0);
  }

  private void setFieldsToValueWhenMoreThanZero(Rooms rooms, int value) {
    if (rooms.bathroom > 0) {
      this.bathroom = value;
    }
    if (rooms.kitchen > 0) {
      this.kitchen = value;
    }
    if (rooms.floor > 0) {
      this.floor = value;
    }
  }
}
