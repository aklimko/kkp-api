package pl.adamklimko.kkp.util;

import pl.adamklimko.kkp.model.ActionType;
import pl.adamklimko.kkp.model.CleanedRooms;
import pl.adamklimko.kkp.model.History;
import pl.adamklimko.kkp.model.ProductsEntry;
import pl.adamklimko.kkp.model.user.AppUser;

import java.util.Date;

public class HistoryUtil {

    public static History getHistoryEntry(AppUser user, ProductsEntry products, CleanedRooms rooms, ActionType actionType) {
        final History historyEntry = new History();
        historyEntry.setTime(new Date());
        historyEntry.setUser(user);
        historyEntry.setProductsEntry(products);
        historyEntry.setRoomsEntry(rooms);
        historyEntry.setActionType(actionType);
        return historyEntry;
    }
}
