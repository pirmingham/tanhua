package com.tanhua.autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 1. 在properties上加入相关注解@Data和@ConfigurationProperties(predix = 'tanhua.sms')
 *
 */
@Data
@ConfigurationProperties(prefix = "tanhua.sms")
public class SmsProperties {

    private String signName;
    private String templateCode;
    private String accessKey;
    private String secret;

}