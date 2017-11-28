package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.Products;

public interface ProductsEntryService {
    Iterable<Products> find();
    void save(Products boughtProducts);
}
