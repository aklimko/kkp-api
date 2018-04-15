package pl.adamklimko.kkp.model.products;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bought_products")
public class BoughtProducts extends Products {

}
