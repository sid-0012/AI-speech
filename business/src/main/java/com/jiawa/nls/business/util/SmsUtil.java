package com.jiawa.nls.business.util;

import com.alibaba.fastjson.JSON;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.jiawa.nls.business.exception.BusinessException;
import com.jiawa.nls.business.exception.BusinessExceptionEnum;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class SmsUtil {

    private static String signName;

    @Value("${sms.aliyun.signName}")
    public void setSignName(String signName) {
        SmsUtil.signName = signName;
    }

    private static String templateCode;

    @Value("${sms.aliyun.templateCode}")
    public void setTemplateCode(String templateCode) {
        SmsUtil.templateCode = templateCode;
    }

    private static String accessKeyId;

    @Value("${sms.aliyun.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        SmsUtil.accessKeyId = accessKeyId;
    }

    private static String accessKeySecret;

    @Value("${sms.aliyun.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        SmsUtil.accessKeySecret = accessKeySecret;
    }

    /**
     * 发送验证码
     * 短信示例：【阿里云短信测试】您正在使用阿里云短信测试服务，体验验证码是：564655，如非本人操作，请忽略本短信！
     * 官方示例代码：https://next.api.aliyun.com/api/Dysmsapi/2017-05-25/SendSms?spm=api-workbench.API%20Document.0.0.5be349a5NCOHvS&tab=DEMO
     * @param mobile
     * @param code
     * @return
     */
    public static SendSmsResponse sendCode(String mobile, String code) {
        log.info("发送验证码手机号：{}，验证码：{}，签名：{}，模板：{}", mobile, code, signName, templateCode);
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                // Please ensure that the environment variables ALIBABA_CLOUD_ACCESS_KEY_ID and ALIBABA_CLOUD_ACCESS_KEY_SECRET are set.
                .accessKeyId(accessKeyId)
                .accessKeySecret(accessKeySecret)
                //.securityToken(System.getenv("ALIBABA_CLOUD_SECURITY_TOKEN")) // use STS token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region("cn-shanghai") // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();
        SendSmsResponse resp = null;

        try {
            // Parameter settings for API request
            SendSmsRequest sendSmsRequest = SendSmsRequest.builder().signName(signName).templateCode(templateCode).phoneNumbers(mobile).templateParam(JSON.toJSONString(map))
                    // Request-level configuration rewrite, can set Http request parameters, etc.
                    // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                    .build();

            // Asynchronously get the return value of the API request
            CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
            // Synchronously get the return value of the API request
            resp = response.get();
            log.info("短信发送结果：{}", JSON.toJSONString(resp));
            // {"body":{"code":"isv.SMS_TEST_NUMBER_LIMIT","message":"只能向已回复授权信息的手机号发送","requestId":"CF9AB1AD-478C-57FB-856F-1F2E6D285270"},"headers":{"Keep-Alive":"timeout=25","Access-Control-Expose-Headers":"*","Access-Control-Allow-Origin":"*","ETag":"1aAOhKX8sBpBTAyuPlMjzAA6","x-acs-request-id":"CF9AB1AD-478C-57FB-856F-1F2E6D285270","Connection":"keep-alive","Content-Length":"148","Date":"Mon, 22 Jan 2024 05:34:28 GMT","Content-Type":"application/json;charset=utf-8","x-acs-trace-id":"62acd0324e6ee15582a267916e94ab95"}}
            // {"body":{"bizId":"806006605901943405^0","code":"OK","message":"OK","requestId":"2EDD16BC-A61A-5A5A-95F3-DFFC4E689548"},"headers":{"Keep-Alive":"timeout=25","Access-Control-Expose-Headers":"*","Access-Control-Allow-Origin":"*","ETag":"1FUChUXkQKE/GIBk4jQdn5A0","x-acs-request-id":"2EDD16BC-A61A-5A5A-95F3-DFFC4E689548","Connection":"keep-alive","Content-Length":"110","Date":"Mon, 22 Jan 2024 05:39:03 GMT","Content-Type":"application/json;charset=utf-8","x-acs-trace-id":"f4ea58c936d4026ded01ef7271039d9d"}}
            if (!"OK".equals(resp.getBody().getCode()) && !"isv.SMS_TEST_NUMBER_LIMIT".equals(resp.getBody().getCode())) {
                throw new BusinessException(BusinessExceptionEnum.SMS_CODE_SEND_ERROR);
            }
            // Asynchronous processing of return values
        /*response.thenAccept(resp -> {
            System.out.println(new Gson().toJson(resp));
        }).exceptionally(throwable -> { // Handling exceptions
            System.out.println(throwable.getMessage());
            return null;
        });*/
        } catch (Exception e) {
            log.error("发短信异常", e);
            throw new BusinessException(BusinessExceptionEnum.SMS_CODE_SEND_ERROR);
        } finally {
            // Finally, close the client
            client.close();
        }
        return resp;
    }

}
