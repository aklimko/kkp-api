package pl.adamklimko.kkp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class BoughtProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    private int toiletPaper;
    private int dishSoap;
    private int trashBag;
    private int soap;
    private int sugar;

    public void addNewBoughtProducts(BoughtProducts boughtProducts) {
        this.toiletPaper += boughtProducts.getToiletPaper();
        this.dishSoap += boughtProducts.getDishSoap();
        this.trashBag += boughtProducts.getTrashBag();
        this.soap += boughtProducts.getSoap();
        this.sugar += boughtProducts.getSugar();
    }
}
