package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.rooms.DirtyRooms;

public interface DirtyRoomsService {
    DirtyRooms find();
    void save(DirtyRooms missingProducts);
}
