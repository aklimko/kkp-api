package pl.adamklimko.kkp.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.adamklimko.kkp.dto.UserDto;

@Data
@Builder
@Document(collection = "Users")
public class UserEntity {

  @Id
  private String id;

  @NotNull
  @Pattern(regexp = "[^\\W_][\\w-]{2,31}")
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
