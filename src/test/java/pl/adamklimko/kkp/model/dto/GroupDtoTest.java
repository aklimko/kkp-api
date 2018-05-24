package pl.adamklimko.kkp.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import javax.validation.ConstraintViolation;
import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

class GroupDtoTest {

  private LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();

  @Before
  public void setup() {
    localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
    localValidatorFactoryBean.afterPropertiesSet();
  }

  @ParameterizedTest
  @CsvSource({
    "Aredos, true"
  })
  public void testUsernameValidation(String name, boolean valid) {
    GroupDto groupDto = new GroupDto();
    groupDto.setName(name);
    Set<ConstraintViolation<GroupDto>> constraints = localValidatorFactoryBean.validate(groupDto, GroupDto.class);
    assertEquals(constraints.size() == 0, valid);
  }
}