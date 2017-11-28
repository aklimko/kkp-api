package pl.adamklimko.kkp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public @Data class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

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

    public Products() {
    }

    public Products(Products products) {
        this.toiletPaper = products.toiletPaper;
        this.dishSoap = products.dishSoap;
        this.trashBag = products.trashBag;
        this.soap = products.soap;
        this.sugar = products.sugar;
    }

    public void addNewBoughtProducts(Products boughtProducts) {
        this.toiletPaper += boughtProducts.toiletPaper;
        this.dishSoap += boughtProducts.dishSoap;
        this.trashBag += boughtProducts.trashBag;
        this.soap += boughtProducts.soap;
        this.sugar += boughtProducts.sugar;
    }
}
