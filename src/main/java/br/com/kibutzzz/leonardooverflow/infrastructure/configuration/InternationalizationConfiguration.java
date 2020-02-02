package br.com.kibutzzz.leonardooverflow.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class InternationalizationConfiguration {

  @Bean
  public ResourceBundleMessageSource messageSource() {
    final ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
    bundle.setBasename("messages");
    bundle.setDefaultEncoding("UTF-8");
    bundle.setUseCodeAsDefaultMessage(true);
    return bundle;
  }

}
