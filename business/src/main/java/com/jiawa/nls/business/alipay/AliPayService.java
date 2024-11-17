package com.jiawa.nls.business.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.common.models.AlipayTradeCloseResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.jiawa.nls.business.exception.BusinessException;
import com.jiawa.nls.business.exception.BusinessExceptionEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AliPayService {

    @Resource
    private AliPayProperties aliPayProperties;

    public AlipayTradePagePayResponse pay(String subject, String outTradeNo, String totalAmount) {
        log.info("调用支付宝下单接口开始，subject：{}，outTradeNo：{}，totalAmount：{}", subject, outTradeNo, totalAmount);
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(getOptions());
        try {
            // 2. 发起API调用（以创建网站支付为例）
            AlipayTradePagePayResponse response = Factory.Payment.Page().optional("qr_pay_mode", "4")
                    .pay(subject, outTradeNo, totalAmount, aliPayProperties.getReturnUrl());
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                log.info("调用支付宝下单接口成功，结果：{}", JSON.toJSONString(response));
                return response;
            } else {
                log.warn("调用支付宝下单接口失败，原因：{}", JSON.toJSONString(response));
                throw new BusinessException(BusinessExceptionEnum.ALIPAY_ERROR);
            }
        } catch (Exception e) {
            log.error("调用支付宝下单接口异常，原因：", e);
            throw new BusinessException(BusinessExceptionEnum.ALIPAY_ERROR);
        }
    }

    public AlipayTradeQueryResponse query(String outTradeNo) {
        Factory.setOptions(getOptions());
        try {
            AlipayTradeQueryResponse response = Factory.Payment.Common().query(outTradeNo);
            if (ResponseChecker.success(response)) {
                log.info("调用支付宝订单查询接口成功，结果：{}", JSON.toJSONString(response));
                return response;
            } else {
                log.warn("调用支付宝订单查询接口失败，原因：{}", JSON.toJSONString(response));
                return response;
            }
        } catch (Exception e) {
            log.error("调用支付宝订单查询接口异常，原因：", e);
            throw new BusinessException(BusinessExceptionEnum.ALIPAY_ERROR);
        }
    }

    public AlipayTradeCloseResponse close(String outTradeNo) {
        Factory.setOptions(getOptions());
        try {
            AlipayTradeCloseResponse response = Factory.Payment.Common().close(outTradeNo);
            if (ResponseChecker.success(response)) {
                log.info("调用支付宝关闭订单接口成功，结果：{}", JSON.toJSONString(response));
                return response;
            } else {
                log.warn("调用支付宝关闭订单接口失败，原因：{}", JSON.toJSONString(response));
                return response;
            }
        } catch (Exception e) {
            log.error("调用支付宝关闭订单接口异常，原因：", e);
            throw new BusinessException(BusinessExceptionEnum.ALIPAY_ERROR);
        }
    }

    public Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = aliPayProperties.getGatewayHost();
        config.signType = "RSA2";
        config.appId = aliPayProperties.getAppId();
        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = aliPayProperties.getMerchantPrivateKey();
        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        // config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
        // config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
        // config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";
        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = aliPayProperties.getAlipayPublicKey();
        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = aliPayProperties.getNotifyUrl();
        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
        config.encryptKey = aliPayProperties.getEncryptKey();
        return config;
    }
}
