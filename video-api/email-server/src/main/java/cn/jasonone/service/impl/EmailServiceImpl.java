package cn.jasonone.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.jasonone.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmailServiceImpl implements EmailService {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Override
    public String sendVerifyCode(String email) {

        String verifycode = RandomUtil.randomString(6);
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("元初视频登录验证码");
        simpleMailMessage.setText("您的验证码是:"+verifycode);
        javaMailSender.send(simpleMailMessage);

        return verifycode;
    }
}
