package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.rooms.CleanedRooms;

public interface CleanedRoomsService {
    CleanedRooms find();
    void save(CleanedRooms rooms);
}
