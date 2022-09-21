package cn.jasonone.controller;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.jasonone.bean.Account;
import cn.jasonone.model.HttpResult;
import cn.jasonone.service.AccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/logicDeleteAccount")
    public HttpResult logicDeleteAccount(@RequestParam("id") Long id){
        int i = accountService.logicDeleteById(id);
        if(i>0){
            Map<String, Object> rs=new HashMap<>();
            rs.put("code",0);
            rs.put("message","封禁成功");
            return HttpResult.ok(rs);
        }
        Map<String, Object> rs=new HashMap<>();
        rs.put("code",10021);
        rs.put("message","封禁失败");
        return HttpResult.ok(rs);

    };

}
