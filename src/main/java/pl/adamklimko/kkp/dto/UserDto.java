package pl.adamklimko.kkp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.adamklimko.kkp.entity.UserEntity;

import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX_MESSAGE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  @NotNull
  @Pattern(regexp = "[^\\W_][\\w-]+", message = NAME_REGEX_MESSAGE)
  @Size(min = 3, max = 32)
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
