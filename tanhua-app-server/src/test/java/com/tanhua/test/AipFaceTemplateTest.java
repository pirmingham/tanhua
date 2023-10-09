package com.tanhua.test;

import com.tanhua.autoconfig.template.AipFaceTemplate;
import com.tanhua.server.AppServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = AppServerApplication.class)
@RunWith(SpringRunner.class)
public class AipFaceTemplateTest {

    @Autowired
    private AipFaceTemplate aipFaceTemplate ;

    @Autowired
    private AipFaceTemplate template;

    @Test
    public void detectFace() {
        String image = "https://pirmingham-sky-take-out.oss-cn-beijing.aliyuncs.com/2023/10/06/33858e97-cdc7-4c68-8f89-b49c9866e73e.png";
        boolean detect = template.detect(image);
        System.out.println(detect);
    }
}
