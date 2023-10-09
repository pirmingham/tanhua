package com.tanhua.autoconfig;

import com.tanhua.autoconfig.properties.OssProperties;
import com.tanhua.autoconfig.properties.SmsProperties;
import com.tanhua.autoconfig.template.OssTemplate;
import com.tanhua.autoconfig.template.SmsTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(
        {SmsProperties.class, OssProperties.class}
)
public class TanhuaAutoConfiguration {


    @Bean
    public SmsTemplate smsTemplate(SmsProperties smsProperties) {
        return new SmsTemplate(smsProperties);
    }
    @Bean
    public OssTemplate ossTemplate(OssProperties ossProperties){
        return new OssTemplate(ossProperties);
    }
}
