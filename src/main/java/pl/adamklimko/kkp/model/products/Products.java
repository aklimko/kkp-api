package pl.adamklimko.kkp.model.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import pl.adamklimko.kkp.model.Views;

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
    private int trashBags;

    @JsonView(Views.Basic.class)
    private int soap;

    @JsonView(Views.Basic.class)
    private int sugar;

    public Products() {
    }

    public Products(Products products) {
        BeanUtils.copyProperties(products, this);
    }

    public void addNewBoughtProducts(Products boughtProducts) {
        this.toiletPaper += boughtProducts.toiletPaper;
        this.dishSoap += boughtProducts.dishSoap;
        this.trashBags += boughtProducts.trashBags;
        this.soap += boughtProducts.soap;
        this.sugar += boughtProducts.sugar;
    }

    public void addNewMissingProducts(Products missingProducts) {
        setFieldsToValueWhenMoreThanZero(missingProducts, 1);
    }

    public void removeMissingProducts(Products boughtProducts) {
        setFieldsToValueWhenMoreThanZero(boughtProducts, 0);
    }

    private void setFieldsToValueWhenMoreThanZero(Products products, int value) {
        if (products.toiletPaper > 0) this.toiletPaper = value;
        if (products.dishSoap > 0) this.dishSoap = value;
        if (products.trashBags > 0) this.trashBags = value;
        if (products.soap > 0) this.soap = value;
        if (products.sugar > 0) this.sugar = value;
    }
}
