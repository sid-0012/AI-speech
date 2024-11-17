package com.jiawa.nls.business.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

    private String name;

    private String audio;

    private String key;

    private BigDecimal amount;

    private String lang;

    private String vid;

}
