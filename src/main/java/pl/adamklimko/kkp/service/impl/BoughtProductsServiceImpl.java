package pl.adamklimko.kkp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.model.BoughtProducts;
import pl.adamklimko.kkp.repository.AppUserRepository;
import pl.adamklimko.kkp.repository.BoughtProductsRepository;
import pl.adamklimko.kkp.service.BoughtProductsService;
import pl.adamklimko.kkp.util.UserUtil;

@Service
public class BoughtProductsServiceImpl implements BoughtProductsService {
    private final BoughtProductsRepository boughtProductsRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public BoughtProductsServiceImpl(BoughtProductsRepository boughtProductsRepository, AppUserRepository appUserRepository) {
        this.boughtProductsRepository = boughtProductsRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public BoughtProducts find() {
        final BoughtProducts boughtProducts = appUserRepository.findByUsername(UserUtil.getUsernameFromContext()).getBoughtProducts();
        if (boughtProducts == null) {
            return new BoughtProducts();
        }
        return boughtProducts;
    }

    @Override
    public void save(BoughtProducts boughtProducts) {
        boughtProductsRepository.save(boughtProducts);
    }
}
