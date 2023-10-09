package com.tanhua.autoconfig.template;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;

public class SmsTemplate {

    public void sendSms(String phone,String code){

        try {
            String accessKeyId = "LTAI5tCTNWWyzHnFK3sCnbGA";
            String accessKeySecret= "aQltzJU4ICvPeuj9dw09RtDGvIsrsX";

            //配置阿里云
            Config config = new Config()
                    // 您的AccessKey ID
                    .setAccessKeyId(accessKeyId)
                    // 您的AccessKey Secret
                    .setAccessKeySecret(accessKeySecret);
            // 访问的域名
            config.endpoint = "dysmsapi.aliyuncs.com";

            com.aliyun.dysmsapi20170525.Client client =  new com.aliyun.dysmsapi20170525.Client(config);

            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(phone)
                    .setSignName("测试模板")
                    .setTemplateCode("SMS_463631983")
                    .setTemplateParam("{\"code\":\""+code+"\"}");
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse response = client.sendSms(sendSmsRequest);

            SendSmsResponseBody body = response.getBody();
            System.out.println(body.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
