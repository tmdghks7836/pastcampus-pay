package com.fastcampuspay.remittance;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.fastcampuspay.common")
@EnableConfigurationProperties(RemittanceConfigurationProperties.class)
public class RemittanceConfiguration {

}
