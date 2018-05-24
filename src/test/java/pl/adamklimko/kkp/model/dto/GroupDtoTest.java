package pl.adamklimko.kkp.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.adamklimko.kkp.model.UserData;

class GroupDtoTest {

  private Validator validator;

  private GroupDto groupDto;

  public GroupDtoTest() {
    setup();
  }

  private void setup() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @BeforeEach
  public void init() {
    groupDto = new GroupDto();
  }

  @ParameterizedTest
  @CsvSource({
      "Aredos, true",
      "_, false",
      "a, false",
      "abc, true",
      "VeryLongNameForAGroupLike, false",
      "VeryLongNameForAGroupLik, true",
      "user_name, true",
      "_username, false",
      "MyUniqueName$, false",
      "Name with spaces, false",
      "   , false"
  })
  public void testNameValidation(String name, boolean valid) {
    groupDto.setName(name);
    groupDto.setUsers(getGroupUsers());

    Set<ConstraintViolation<GroupDto>> constraints = validator.validate(groupDto);
    String message =
        String.format("Name: '%s' should be %s", name, valid ? "valid" : "not valid");
    assertEquals(valid, constraints.size() == 0, message);
  }

  private List<UserData> getGroupUsers() {
    List<UserData> users = new ArrayList<>();
    users.add(UserData.builder()
        .username("valid-username")
        .role("USER")
        .build());
    return users;
  }

  @Test
  public void testGroupUsersNull() {
    groupDto.setName("ValidName");

    Set<ConstraintViolation<GroupDto>> constraints = validator.validate(groupDto);

    assertFalse(constraints.size() == 0);
  }

  @Test
  public void testGroupUsersEmpty() {
    groupDto.setName("ValidName");
    groupDto.setUsers(new ArrayList<>());

    Set<ConstraintViolation<GroupDto>> constraints = validator.validate(groupDto);

    assertFalse(constraints.size() == 0);
  }

  @Test
  public void testGroupUsersWithOneUser() {
    groupDto.setName("ValidName");
    ArrayList<UserData> users = new ArrayList<>();
    users.add(UserData.builder().username("ValidName").role("USER").build());
    groupDto.setUsers(users);

    Set<ConstraintViolation<GroupDto>> constraints = validator.validate(groupDto);

    assertTrue(constraints.size() == 0);
  }
}