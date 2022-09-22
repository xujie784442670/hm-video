package cn.jasonone.controller;


import cn.jasonone.model.HttpResult;
import cn.jasonone.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Resource
    private EmailService emailService;
    // private Map<String,String> verifyCodeMaps= new HashMap<>();

    @Resource
    private ServletContext application;
    @GetMapping("/verifycode")
    public HttpResult sendVerifyCode(String email){
        String verifycode = emailService.sendVerifyCode(email);
        Map<String,String> verifyCodeMaps= (Map<String,String>)application.getAttribute("verifyCodeMaps");
        if(verifyCodeMaps==null){
            verifyCodeMaps=new HashMap<>();
            application.setAttribute("verifyCodeMaps",verifyCodeMaps);
        }
        verifyCodeMaps.put(email,verifycode);
        return HttpResult.ok();
    }
}