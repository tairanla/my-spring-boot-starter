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
