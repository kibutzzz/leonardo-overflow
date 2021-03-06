package br.com.kibutzzz.leonardooverflow.infrastructure.configuration.security;

import br.com.kibutzzz.leonardooverflow.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AuthenticationService authenticationService;

  private final TokenService tokenService;

  private final UserService userService;

  //authentication configuration
  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
  }

  //authorization configuration
  @Override
  protected void configure(final HttpSecurity http) throws Exception {

    http
      .cors().and()
      .csrf().disable()
      .authorizeRequests()
      //public endpoints
      .antMatchers(HttpMethod.POST, "/auth").permitAll()
      .antMatchers(HttpMethod.GET, "/question").permitAll()
      .antMatchers(HttpMethod.GET, "/question/*").permitAll()
      .antMatchers(HttpMethod.GET, "/question/tag/*").permitAll()
      .antMatchers(HttpMethod.GET, "/question/search/*").permitAll()
      .antMatchers(HttpMethod.GET, "/question/user/*").permitAll()
      .antMatchers(HttpMethod.POST, "/user").permitAll()
      .antMatchers(HttpMethod.GET, "/user/validate/*").permitAll()
      .antMatchers(HttpMethod.GET, "/tag").permitAll()
      .antMatchers(HttpMethod.GET, "/tag/*").permitAll()


      //other requests must be authenticated
      .anyRequest().authenticated()
      .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().addFilterBefore(new TokenAuthenticationFilter(tokenService, userService),
      UsernamePasswordAuthenticationFilter.class);

  }

  //static resources configuration
  @Override
  public void configure(final WebSecurity web) {

  }

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

}
