package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.CleanedRooms;

public interface CleanedRoomsService {
    CleanedRooms find();
    void save(CleanedRooms cleanedRooms);
}
