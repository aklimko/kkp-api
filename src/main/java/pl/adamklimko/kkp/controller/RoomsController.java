package pl.adamklimko.kkp.controller;

import org.springframework.web.bind.annotation.*;
import pl.adamklimko.kkp.config.patch.json.Patch;
import pl.adamklimko.kkp.model.ActionType;
import pl.adamklimko.kkp.model.rooms.CleanedRooms;
import pl.adamklimko.kkp.model.rooms.DirtyRooms;
import pl.adamklimko.kkp.model.rooms.Rooms;
import pl.adamklimko.kkp.model.rooms.RoomsEntry;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.model.user.Profile;
import pl.adamklimko.kkp.service.*;
import pl.adamklimko.kkp.util.HistoryUtil;
import pl.adamklimko.kkp.util.UserUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class RoomsController {
    private final AppUserService appUserService;
    private final CleanedRoomsService cleanedRoomsService;
    private final DirtyRoomsService dirtyRoomsService;
    private final HistoryService historyService;

    public RoomsController(AppUserService appUserService, CleanedRoomsService cleanedRoomsService, DirtyRoomsService dirtyRoomsService, HistoryService historyService) {
        this.appUserService = appUserService;
        this.cleanedRoomsService = cleanedRoomsService;
        this.dirtyRoomsService = dirtyRoomsService;
        this.historyService = historyService;
    }

    @GetMapping("/all")
    public Map<String, Rooms> getCleanedRoomsFromAllUsers() {
        final List<AppUser> users = appUserService.findAll();
        Map<String, Rooms> usersCleanedRooms = new HashMap<>(users.size());
        for (AppUser user : users) {
            final Profile profile = user.getProfile();
            if (profile == null) {
                usersCleanedRooms.put(user.getUsername(), user.getCleanedRooms());
                continue;
            }
            final String fullName = user.getProfile().getFullName();
            if (fullName != null && fullName.length() > 0) {
                usersCleanedRooms.put(fullName, user.getCleanedRooms());
            } else {
                usersCleanedRooms.put(user.getUsername(), user.getCleanedRooms());
            }
        }
        return usersCleanedRooms;
    }

    @GetMapping("cleaned")
    public CleanedRooms getCleanedRooms() {
        return cleanedRoomsService.find();
    }

    @PatchMapping("cleaned")
    @Patch(id = Long.class, service = BoughtProductsService.class)
    public CleanedRooms patchCleanedRooms(@RequestBody CleanedRooms cleanedRooms) {
        final AppUser user = appUserService.findByUsername(UserUtil.getUsernameFromContext());
        if (user == null) {
            return null;
        }
        final CleanedRooms userCleanedRooms = user.getCleanedRooms();
        if (userCleanedRooms == null) {
            user.setCleanedRooms(cleanedRooms);
        } else {
            userCleanedRooms.addNewCleanedRooms(cleanedRooms);
        }

        final DirtyRooms dirtyRooms = dirtyRoomsService.find();
        if (dirtyRooms != null) {
            dirtyRooms.removeDirtyRooms(cleanedRooms);
        }

        final CleanedRooms cleanedRoomsAfterAddition = user.getCleanedRooms();
        RoomsEntry roomsEntry = new RoomsEntry(cleanedRooms);
        historyService.save(HistoryUtil.getHistoryEntry(user, null, roomsEntry, ActionType.DONE));
        return cleanedRoomsAfterAddition;
    }

    @GetMapping("dirty")
    public DirtyRooms getDirtyRooms() {
        return dirtyRoomsService.find();
    }

    @PatchMapping("dirty")
    @Patch(id = Long.class, service = MissingProductsService.class)
    public DirtyRooms patchDirtyRooms(@RequestBody DirtyRooms dirtyRooms) {
        final DirtyRooms dirtyRoomsFromDb = dirtyRoomsService.find();
        if (dirtyRoomsFromDb == null) {
            dirtyRoomsService.save(dirtyRooms);
        } else {
            dirtyRoomsFromDb.addNewDirtyRooms(dirtyRooms);
            dirtyRoomsService.save(dirtyRoomsFromDb);
        }

        final AppUser user = appUserService.findByUsername(UserUtil.getUsernameFromContext());
        if (user == null) {
            return null;
        }
        final RoomsEntry roomsEntry = new RoomsEntry(dirtyRooms);
        historyService.save(HistoryUtil.getHistoryEntry(user, null, roomsEntry, ActionType.TO_BE_DONE));
        return dirtyRoomsFromDb;
    }
}
