package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.products.MissingProducts;

public interface MissingProductsService {
    MissingProducts find();
    void save(MissingProducts missingProducts);
}
