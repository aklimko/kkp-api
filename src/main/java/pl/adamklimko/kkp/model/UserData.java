package pl.adamklimko.kkp.model;

import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX;
import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX_MESSAGE;

import java.util.Map;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserData {

  @NotNull
  @Pattern(regexp = NAME_REGEX, message = NAME_REGEX_MESSAGE)
  @Size(min = 3, max = 32)
  private String username;

  @NotNull
  private String role;

  private Map<String, Long> productsBought;
}
