package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.products.BoughtProducts;

public interface BoughtProductsService {
    BoughtProducts find();
    void save(BoughtProducts boughtProducts);
}
