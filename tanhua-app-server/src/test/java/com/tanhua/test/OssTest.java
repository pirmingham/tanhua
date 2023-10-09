package com.tanhua.test;

import com.tanhua.autoconfig.template.OssTemplate;
import com.tanhua.server.AppServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppServerApplication.class)
public class OssTest {

    @Autowired
    private OssTemplate template;

    @Test
    public void testTemplateUpload() throws FileNotFoundException {
        String path = "C:\\Users\\pirmingham\\Desktop\\探花项目学习笔记\\03探花项目 验证码登录.assets\\接口.png";
        FileInputStream inputStream = new FileInputStream(new File(path));
        String imageUrl = template.upload(path, inputStream);
        System.out.println(imageUrl);
    }
}