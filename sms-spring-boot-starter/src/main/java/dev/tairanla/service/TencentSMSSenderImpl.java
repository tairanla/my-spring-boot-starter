package dev.tairanla.service;

import dev.tairanla.SMSProperties;

/**
 * TencentSMSSenderImpl: 腾讯云 SMS 发送服务实现
 *
 * @author poneding@gmail.com
 * @date 2025/8/28 23:27
 */
public class TencentSMSSenderImpl implements SMSSender {
    private SMSProperties.SMSMessage smsMessage;

    public TencentSMSSenderImpl(SMSProperties.SMSMessage smsMessage) {
        this.smsMessage = smsMessage;
    }

    @Override
    public boolean send(String message) {
        System.out.println(this.smsMessage.toString()+" Send message: "+message);
        return true;
    }
}
