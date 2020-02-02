package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  componentModel = "spring",
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BaseMapperConfig {
}
