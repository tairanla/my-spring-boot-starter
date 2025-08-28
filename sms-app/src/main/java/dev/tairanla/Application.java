package dev.tairanla;

import dev.tairanla.service.AliyunSMSSenderImpl;
import dev.tairanla.service.TencentSMSSenderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Application:
 *
 * @author poneding@gmail.com
 * @date 2025/8/29 00:06
 */
@SpringBootApplication
// @EnableSMS
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        AliyunSMSSenderImpl aliyunSMSSender = applicationContext.getBean(AliyunSMSSenderImpl.class);
        aliyunSMSSender.send("用阿里云发送短信");

        TencentSMSSenderImpl tencentSMSSender = applicationContext.getBean(TencentSMSSenderImpl.class);
        tencentSMSSender.send("用腾讯云发送短信");
    }
}
