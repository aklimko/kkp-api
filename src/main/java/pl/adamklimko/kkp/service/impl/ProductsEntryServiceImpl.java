package pl.adamklimko.kkp.service.impl;

import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.model.Products;
import pl.adamklimko.kkp.repository.AppUserRepository;
import pl.adamklimko.kkp.repository.ProductsEntryRepository;
import pl.adamklimko.kkp.service.ProductsEntryService;

import java.util.ArrayList;

@Service
public class ProductsEntryServiceImpl implements ProductsEntryService {
    private final ProductsEntryRepository productsEntryRepository;
    private final AppUserRepository appUserRepository;

    public ProductsEntryServiceImpl(ProductsEntryRepository productsEntryRepository, AppUserRepository appUserRepository) {
        this.productsEntryRepository = productsEntryRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Iterable<Products> find() {
        final Iterable<Products> boughtProducts = productsEntryRepository.findAll();
        if (boughtProducts == null) {
            return new ArrayList<>();
        }
        return boughtProducts;
    }

    @Override
    public void save(Products productsEntry) {
        productsEntryRepository.save(productsEntry);
    }
}
