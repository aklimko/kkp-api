package pl.adamklimko.kkp.model.products;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products_entry")
public class ProductsEntry extends Products {
    public ProductsEntry() {
    }

    public ProductsEntry(Products products) {
        super(products);
    }
}
