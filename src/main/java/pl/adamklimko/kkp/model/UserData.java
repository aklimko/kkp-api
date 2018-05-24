package pl.adamklimko.kkp.model;

import java.util.Map;
import lombok.Data;

@Data
public class UserData {

  private String username;

  private String role;

  private Map<String, Long> productsBought;
}
