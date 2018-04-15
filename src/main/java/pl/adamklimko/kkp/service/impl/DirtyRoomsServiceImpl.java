package pl.adamklimko.kkp.service.impl;

import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.model.rooms.DirtyRooms;
import pl.adamklimko.kkp.repository.DirtyRoomsRepository;
import pl.adamklimko.kkp.service.DirtyRoomsService;

@Service
public class DirtyRoomsServiceImpl implements DirtyRoomsService {

  private final DirtyRoomsRepository dirtyRoomsRepository;

  public DirtyRoomsServiceImpl(DirtyRoomsRepository dirtyRoomsRepository) {
    this.dirtyRoomsRepository = dirtyRoomsRepository;
  }

  @Override
  public DirtyRooms find() {
    return dirtyRoomsRepository.findById(1L)
        .orElse(null);
  }

  @Override
  public void save(DirtyRooms missingProducts) {
    dirtyRoomsRepository.save(missingProducts);
  }
}
