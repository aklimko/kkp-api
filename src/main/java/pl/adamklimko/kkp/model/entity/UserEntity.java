package pl.adamklimko.kkp.model.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.adamklimko.kkp.model.dto.UserDto;

import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX;
import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX_MESSAGE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserEntity {

  @Id
  private String id;

  @NotNull
  @Pattern(regexp = NAME_REGEX, message = NAME_REGEX_MESSAGE)
  @Size(min = 3, max = 32)
  @Indexed(unique = true)
  private String username;

  @NotNull
  @Size(min = 6, max = 64)
  private String password;

  public UserDto toDto() {
    return UserDto.builder()
        .username(username)
        .password(password)
        .build();
  }
}
