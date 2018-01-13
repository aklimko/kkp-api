package pl.adamklimko.kkp.model;

import com.fasterxml.jackson.annotation.JsonView;
import pl.adamklimko.kkp.model.products.MissingProducts;
import pl.adamklimko.kkp.model.rooms.DirtyRooms;
import pl.adamklimko.kkp.model.user.AppUser;

import java.util.List;

public class Data {
    @JsonView(Views.Basic.class)
    private List<AppUser> usersData;

    @JsonView(Views.Basic.class)
    private Iterable<History> history;

    @JsonView(Views.Basic.class)
    private MissingProducts missingProducts;

    @JsonView(Views.Basic.class)
    private DirtyRooms dirtyRooms;

    public Data(List<AppUser> usersData, Iterable<History> history, MissingProducts missingProducts, DirtyRooms dirtyRooms) {
        this.usersData = usersData;
        this.history = history;
        this.missingProducts = missingProducts;
        this.dirtyRooms = dirtyRooms;
    }

    public List<AppUser> getUsersData() {
        return usersData;
    }

    public Iterable<History> getHistory() {
        return history;
    }

    public MissingProducts getMissingProducts() {
        return missingProducts;
    }

    public DirtyRooms getDirtyRooms() {
        return dirtyRooms;
    }
}
