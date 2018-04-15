package pl.adamklimko.kkp.config;

import static com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer.ZONED_DATE_TIME;

import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.adamklimko.kkp.config.patch.resolver.PartialUpdateArgumentResolver;

/**
 * Credit to https://github.com/countrogue for whole Spring HTTP PATCH fix
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  ApplicationContext applicationContext;
  PartialUpdateArgumentResolver partialUpdateArgumentResolver;

  @Autowired
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Autowired
  public void setPartialUpdateArgumentResolver(
      PartialUpdateArgumentResolver partialUpdateArgumentResolver) {
    this.partialUpdateArgumentResolver = partialUpdateArgumentResolver;
  }

  /**
   * Adds the patch argument resolver
   */
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(partialUpdateArgumentResolver);
  }

  @Bean
  @Primary
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder
        .serializerByType(ZonedDateTime.class, ZonedDateTimeSerializer.INSTANCE)
        .deserializerByType(ZonedDateTime.class, ZONED_DATE_TIME)
        .applicationContext(applicationContext);
    return builder;
  }
}
