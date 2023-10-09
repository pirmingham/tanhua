package com.tanhua.test;

import com.tanhua.autoconfig.template.SmsTemplate;
import com.tanhua.server.AppServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppServerApplication.class)
public class SmsTemplateTest {
    @Autowired
    private SmsTemplate smsTemplate;
    @Test
    public void test(){
        smsTemplate.sendSms("13550660181","123456");
    }
}
