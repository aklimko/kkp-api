package pl.adamklimko.kkp.model.dto;

import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX;
import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX_MESSAGE;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.adamklimko.kkp.model.UserData;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

  @NotNull
  @Pattern(regexp = NAME_REGEX, message = NAME_REGEX_MESSAGE)
  @Size(min = 3, max = 24)
  private String name;

  private List<UserData> users;

  private List<String> products;

  private List<String> productsMissing;
}
