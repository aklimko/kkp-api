package pl.adamklimko.kkp.model.dto;

import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX;
import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX_MESSAGE;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class GroupCreateDto {

  @NotNull
  @Pattern(regexp = NAME_REGEX, message = NAME_REGEX_MESSAGE)
  @Size(min = 3, max = 24)
  private String name;
}
