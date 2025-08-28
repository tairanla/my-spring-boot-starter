package dev.tairanla;

import dev.tairanla.service.AliyunSMSSenderImpl;
import dev.tairanla.service.TencentSMSSenderImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SMSAutoConfiguration:
 *
 * @author poneding@gmail.com
 * @date 2025/8/28 23:24
 */
@EnableConfigurationProperties(value = SMSProperties.class)
@Configuration
public class SMSAutoConfiguration {
    @Bean
    public AliyunSMSSenderImpl aliyunSMSSender(SMSProperties properties){
        return new AliyunSMSSenderImpl(properties.getAliyun());
    }

    @Bean
    public TencentSMSSenderImpl tencentSMSSender(SMSProperties properties){
        return new TencentSMSSenderImpl(properties.getTencent());
    }
}
