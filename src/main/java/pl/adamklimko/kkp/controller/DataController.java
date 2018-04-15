package pl.adamklimko.kkp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adamklimko.kkp.model.Data;
import pl.adamklimko.kkp.model.History;
import pl.adamklimko.kkp.model.Views;
import pl.adamklimko.kkp.model.products.MissingProducts;
import pl.adamklimko.kkp.model.rooms.DirtyRooms;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.service.AppUserService;
import pl.adamklimko.kkp.service.DirtyRoomsService;
import pl.adamklimko.kkp.service.HistoryService;
import pl.adamklimko.kkp.service.MissingProductsService;

@RestController
@RequestMapping("data")
public class DataController {

  private final AppUserService appUserService;
  private final HistoryService historyService;
  private final MissingProductsService missingProductsService;
  private final DirtyRoomsService dirtyRoomsService;

  public DataController(AppUserService appUserService, HistoryService historyService,
      MissingProductsService missingProductsService, DirtyRoomsService dirtyRoomsService) {
    this.appUserService = appUserService;
    this.historyService = historyService;
    this.missingProductsService = missingProductsService;
    this.dirtyRoomsService = dirtyRoomsService;
  }

  @JsonView(Views.Extended.class)
  @GetMapping()
  public Data getData() {
    final List<AppUser> usersData = appUserService.findAll();
    final Iterable<History> history = historyService.findLast20();
    final MissingProducts missingProducts = missingProductsService.find();
    final DirtyRooms dirtyRooms = dirtyRoomsService.find();
    return new Data(usersData, history, missingProducts, dirtyRooms);
  }
}
