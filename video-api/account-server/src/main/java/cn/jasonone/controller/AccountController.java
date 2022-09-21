package cn.jasonone.controller;

import cn.jasonone.bean.Account;
import cn.jasonone.model.HttpResult;
import cn.jasonone.service.AccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Validated
@RestController
@RequestMapping("/user")
public class AccountController {
    @Resource
    private AccountService accountService;
    @PostMapping("/updatePassword")
//    @SaCheckPermission("user:updatePassword")
    public HttpResult updatePassword(@RequestBody Account account){
        int i = accountService.updatePassword(account);
        if(i>0){
            return HttpResult.ok();
        }else {
            return HttpResult.error(10061,"密码修改失败");
        }
    }
    @PostMapping("/updateEmail")
    //    @SaCheckPermission("user:updateEmail")
    public HttpResult updateEmail(@RequestBody Account account){
        Map<String, Object> rs=new HashMap<>();
        int i = accountService.updateEmail(account);
        if(i>0){
            return HttpResult.ok();
        }else {
            return HttpResult.error(10081,"邮箱修改失败");
        }
    }

    @PostMapping("/updateNickname")
    public HttpResult updateNickname(@RequestBody Account account){
        System.out.println(account);
        Map<String, Object> rs=new HashMap<>();
        System.out.println("55");
        int i = accountService.updateNickname(account);
        if(i>0){
            rs.put("code",0);
            rs.put("msg","昵称修改成功！");
        }else {
            rs.put("code",1);
            rs.put("msg","昵称修改失败！");
        }
        return HttpResult.ok(rs);
    }
}
