package com.jiawa.nls.business.alipay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayProperties {

    private String gatewayHttpsHost;

    private String gatewayHost;

    private String appId;

    private String merchantPrivateKey;

    private String alipayPublicKey;

    private String notifyUrl;

    private String encryptKey;

    private String returnUrl;

}
