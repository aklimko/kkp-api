package pl.adamklimko.kkp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.model.rooms.CleanedRooms;
import pl.adamklimko.kkp.repository.AppUserRepository;
import pl.adamklimko.kkp.repository.CleanedRoomsRepository;
import pl.adamklimko.kkp.service.CleanedRoomsService;
import pl.adamklimko.kkp.util.UserUtil;

@Service
public class CleanedRoomsServiceImpl implements CleanedRoomsService {
    private final CleanedRoomsRepository cleanedRoomsRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public CleanedRoomsServiceImpl(CleanedRoomsRepository cleanedRoomsRepository, AppUserRepository appUserRepository) {
        this.cleanedRoomsRepository = cleanedRoomsRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public CleanedRooms find() {
        final CleanedRooms cleanedRooms = appUserRepository.findByUsername(UserUtil.getUsernameFromContext()).getCleanedRooms();
        if (cleanedRooms == null) {
            return new CleanedRooms();
        }
        return cleanedRooms;
    }

    @Override
    public void save(CleanedRooms cleanedRooms) {
        cleanedRoomsRepository.save(cleanedRooms);
    }
}
