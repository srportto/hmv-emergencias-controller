package br.com.hmv.models.mappers;

import org.mapstruct.ReportingPolicy;

@org.mapstruct.MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperConfig {
}
