package pl.adamklimko.kkp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.adamklimko.kkp.entity.UserEntity;

import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX;
import static pl.adamklimko.kkp.util.ValidationStrings.NAME_REGEX_MESSAGE;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  @Getter
  @NotNull
  @Pattern(regexp = NAME_REGEX, message = NAME_REGEX_MESSAGE)
  @Size(min = 3, max = 32)
  private String username;

  @NotNull
  @Size(min = 6, max = 64)
  private String password;

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  public UserEntity toEntity() {
    return UserEntity.builder()
        .username(username)
        .password(password)
        .build();
  }
}
