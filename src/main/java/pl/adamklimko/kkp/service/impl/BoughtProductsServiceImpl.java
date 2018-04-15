package pl.adamklimko.kkp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.model.products.BoughtProducts;
import pl.adamklimko.kkp.repository.AppUserRepository;
import pl.adamklimko.kkp.repository.ProductsRepository;
import pl.adamklimko.kkp.service.BoughtProductsService;
import pl.adamklimko.kkp.util.UserUtil;

@Service
public class BoughtProductsServiceImpl implements BoughtProductsService {

  private final ProductsRepository productsRepository;
  private final AppUserRepository appUserRepository;

  @Autowired
  public BoughtProductsServiceImpl(ProductsRepository productsRepository,
      AppUserRepository appUserRepository) {
    this.productsRepository = productsRepository;
    this.appUserRepository = appUserRepository;
  }

  @Override
  public BoughtProducts find() {
    final BoughtProducts boughtProducts = appUserRepository
        .findByUsername(UserUtil.getUsernameFromContext()).getBoughtProducts();
    if (boughtProducts == null) {
      return new BoughtProducts();
    }
    return boughtProducts;
  }

  @Override
  public void save(BoughtProducts boughtProducts) {
    productsRepository.save(boughtProducts);
  }
}
