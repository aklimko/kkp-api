package pl.adamklimko.kkp.service.impl;

import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.model.products.MissingProducts;
import pl.adamklimko.kkp.repository.MissingProductsRepository;
import pl.adamklimko.kkp.service.MissingProductsService;

@Service
public class MissingProductsServiceImpl implements MissingProductsService {
    private final MissingProductsRepository missingProductsRepository;

    public MissingProductsServiceImpl(MissingProductsRepository missingProductsRepository) {
        this.missingProductsRepository = missingProductsRepository;
    }

    @Override
    public MissingProducts find() {
        return missingProductsRepository.findOne(1L);
    }

    @Override
    public void save(MissingProducts missingProducts) {
        missingProductsRepository.save(missingProducts);
    }
}
