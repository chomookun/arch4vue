package org.chomookun.arch4vue.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app.config")
@Lazy(false)
@Validated
@AllArgsConstructor
@Getter
public class Config {

    private String apiBaseUrl;

}
