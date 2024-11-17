package com.jiawa.nls.business.nls;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "nls.filetrans")
public class NlsFiletransProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String regionId;
    private String endpointName;
    private String product;
    private String domain;
    private String version;
    private String taskVersion;
    private String callback;

}
