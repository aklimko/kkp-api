package pl.adamklimko.kkp.model.dto;

import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX;
import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX_MESSAGE;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import pl.adamklimko.kkp.model.entity.UserEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  @NotNull
  @Pattern(regexp = NAME_REGEX, message = NAME_REGEX_MESSAGE)
  @Size(min = 3, max = 32)
  @Indexed(unique = true)
  private String username;

  @NotNull
  @Size(min = 6, max = 64)
  private String password;

  public UserEntity toEntity() {
    return UserEntity.builder()
        .username(username)
        .password(password)
        .build();
  }
}