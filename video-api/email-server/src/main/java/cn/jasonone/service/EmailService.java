package cn.jasonone.service;

public interface EmailService {
    /**
     * 发送验证码
     * @param email 邮箱
     * @return 验证码
     */
    String sendVerifyCode(String email);
}
