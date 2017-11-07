package pl.adamklimko.kkp.controller;

import org.springframework.web.bind.annotation.*;
import pl.adamklimko.kkp.config.patch.json.Patch;
import pl.adamklimko.kkp.model.CleanedRooms;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.service.AppUserService;
import pl.adamklimko.kkp.service.BoughtProductsService;
import pl.adamklimko.kkp.service.CleanedRoomsService;
import pl.adamklimko.kkp.util.UserUtil;

@RestController
@RequestMapping("/cleaned_rooms")
public class CleanedRoomsController {
    private final AppUserService appUserService;
    private final CleanedRoomsService cleanedRoomsService;

    public CleanedRoomsController(AppUserService appUserService, CleanedRoomsService cleanedRoomsService) {
        this.appUserService = appUserService;
        this.cleanedRoomsService = cleanedRoomsService;
    }

    @GetMapping()
    public CleanedRooms getCleanedRooms() {
        return cleanedRoomsService.find();
    }

    @PatchMapping()
    @Patch(id = Integer.class, service = BoughtProductsService.class)
    public CleanedRooms patchCleanedRooms(@RequestBody CleanedRooms cleanedRooms) {
        final AppUser user = appUserService.findByUsername(UserUtil.getUsernameFromContext());
        final CleanedRooms userCleanedRooms = user.getCleanedRooms();
        if (userCleanedRooms == null) {
            user.setCleanedRooms(cleanedRooms);
        } else {
            userCleanedRooms.addNewCleanedRooms(cleanedRooms);
        }
        final CleanedRooms cleanedRoomsAfterAddition = user.getCleanedRooms();
        cleanedRoomsService.save(cleanedRoomsAfterAddition);
        return cleanedRoomsAfterAddition;
    }
}
