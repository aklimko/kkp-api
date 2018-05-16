package pl.adamklimko.kkp.model.entity;


import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.adamklimko.kkp.model.UserData;

import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX;
import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX_MESSAGE;

@Data
@Builder
@Document(collection = "groups")
public class GroupEntity {

  @Id
  private String id;

  @NotNull
  @Pattern(regexp = NAME_REGEX, message = NAME_REGEX_MESSAGE)
  @Size(min = 3, max = 24)
  @Indexed(unique = true)
  private String name;

  @NotNull
  private List<UserData> users;

  private List<String> products;

  private List<String> productsMissing;
}
