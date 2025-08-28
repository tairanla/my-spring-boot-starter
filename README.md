# sms-spring-boot-starter

自定义一个 Spring Boot Starter 来集成短信服务。

自定义 Spring Boot Starter 一般包含相同的两个部分：

1. 自动配置类：AutoConfiguration
2. 配置属性类：ConfigurationProperties (约定大于配置)

## 1. 创建项目

使用 Spring Initializr 创建一个新的 Maven 项目，添加以下依赖：

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.38</version>
            <scope>provided</scope>
        </dependency>
```

## 2. 创建配置类

创建一个配置类 `SMSProperties`，用于读取配置文件中的短信服务相关配置：

```java
package dev.tairanla;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 // * SMSProperties: SMS 配置
 *
 * @author poneding@gmail.com
 * @date 2025/8/28 23:01
 */
@ConfigurationProperties(prefix = "sms")
@Data
public class SMSProperties {
    private SMSMessage aliyun =new SMSMessage();
    private SMSMessage tencent = new SMSMessage();

    @Data
    public static class SMSMessage{
        private String username;
        private String password;
        private String sign;
        private String url;
        @Override
        public String toString(){
            return "SMSMessage(username:"+username+",password:"+password+",sign:"+sign+",url:"+url+")";
        }
    }
}
```

## 3. 创建自动配置类

创建一个自动配置类 `SMSAutoConfiguration`，用于根据配置类创建短信服务实例：

```java
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
```

## 集成应用

starter 集成的两种方式，选择其中一种即可，最熟悉的方式是第一种：

1. Spring SPI 机制加载我们自定义的 starter，在 `src/main/resources/META-INF/spring.factories` 文件中添加：

```txt
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
dev.tairanla.sms.starter.SMSAutoConfiguration
```

2. 在 starter 组件集成到 Spring Boot 应用时，主动声明启用该 starter，通过定义 @Enable 注解，然后再把自动配置类通过 Import 注解引入进来：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SMSAutoConfiguration.class})
public @interface EnableSMS {
}
```

## 使用示例

在 Spring Boot 应用的主类上添加 @EnableSMS 注解，启用短信服务：

```java
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
```
