package br.com.kibutzzz.leonardooverflow;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("full-context-test")
@SpringBootTest
public class LeonardoOverflowApplicationTests {

  @Test
  public void test_application_shouldLoadContext() {

    final ConfigurableApplicationContext application = SpringApplication.run(LeonardoOverflowApplication.class);
    assertTrue(application.isActive());
  }

}
