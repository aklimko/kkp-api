package pl.adamklimko.kkp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class BoughtProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    @JsonView(Views.Basic.class)
    private int toiletPaper;

    @JsonView(Views.Basic.class)
    private int dishSoap;

    @JsonView(Views.Basic.class)
    private int trashBag;

    @JsonView(Views.Basic.class)
    private int soap;

    @JsonView(Views.Basic.class)
    private int sugar;

    public void addNewBoughtProducts(BoughtProducts boughtProducts) {
        this.toiletPaper += boughtProducts.getToiletPaper();
        this.dishSoap += boughtProducts.getDishSoap();
        this.trashBag += boughtProducts.getTrashBag();
        this.soap += boughtProducts.getSoap();
        this.sugar += boughtProducts.getSugar();
    }
}
