package dev.tairanla.service;

/**
 * SMSMessage:
 *
 * @author poneding@gmail.com
 * @date: 2025/8/28 23:26
 */
public interface SMSSender {
    boolean send(String message);
}
