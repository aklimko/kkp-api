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
    final History historyEntry = new History();
    historyEntry.setTime(LocalDateTime.now());
    historyEntry.setUser(user);
    historyEntry.setProductsEntry(products);
    historyEntry.setRoomsEntry(rooms);
    historyEntry.setActionType(actionType);
    return historyEntry;
  }
}
