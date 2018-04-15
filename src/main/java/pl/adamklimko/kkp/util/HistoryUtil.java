package pl.adamklimko.kkp.util;

import java.time.LocalDateTime;
import pl.adamklimko.kkp.model.ActionType;
import pl.adamklimko.kkp.model.History;
import pl.adamklimko.kkp.model.products.ProductsEntry;
import pl.adamklimko.kkp.model.rooms.RoomsEntry;
import pl.adamklimko.kkp.model.user.AppUser;

public class HistoryUtil {

  public static History getHistoryEntry(AppUser user, ProductsEntry products, RoomsEntry rooms,
      ActionType actionType) {
    return History.builder()
        .time(LocalDateTime.now())
        .user(user)
        .productsEntry(products)
        .roomsEntry(rooms)
        .actionType(actionType)
        .build();
  }
}
