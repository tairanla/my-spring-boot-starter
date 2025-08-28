package dev.tairanla.service;

import dev.tairanla.SMSProperties;

/**
 * AliyunSMSSenderImpl: 阿里云 SMS 发送服务实现
 *
 * @author poneding@gmail.com
 * @date 2025/8/28 23:26
 */
public class AliyunSMSSenderImpl implements  SMSSender {

    private SMSProperties.SMSMessage smsMessage;

    public AliyunSMSSenderImpl(SMSProperties.SMSMessage smsMessage) {
        this.smsMessage = smsMessage;
    }

    @Override
    public boolean send(String message) {
        System.out.println(this.smsMessage.toString()+" Send message: "+message);
        return true;
    }
}
