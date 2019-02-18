#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.context.annotation.Configuration;

import ${groupId}.base.infra.spring.config.FiltroSegurancaConfig;

@Configuration
public class SegurancaConfig extends FiltroSegurancaConfig {
}