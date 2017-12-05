package pl.adamklimko.kkp.util;

import pl.adamklimko.kkp.model.ActionType;
import pl.adamklimko.kkp.model.History;
import pl.adamklimko.kkp.model.products.ProductsEntry;
import pl.adamklimko.kkp.model.rooms.CleanedRooms;
import pl.adamklimko.kkp.model.user.AppUser;

import java.time.LocalDateTime;

public class HistoryUtil {

    public static History getHistoryEntry(AppUser user, ProductsEntry products, CleanedRooms rooms, ActionType actionType) {
        final History historyEntry = new History();
        historyEntry.setTime(LocalDateTime.now());
        historyEntry.setUser(user);
        historyEntry.setProductsEntry(products);
        historyEntry.setRoomsEntry(rooms);
        historyEntry.setActionType(actionType);
        return historyEntry;
    }
}
