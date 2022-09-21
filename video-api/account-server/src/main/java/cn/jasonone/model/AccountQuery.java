package cn.jasonone.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * 用户表查询对象
 */
@Data
//@Validated
public class AccountQuery implements Serializable {

    public interface Register {
    }

    public interface Login {
    }

    public interface EmailLogin {
    }

    /**
     * 主键;主键
     */
    private Long id;

    /**
     * 用户名;用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {Register.class, Login.class})
    @Length(min = 2, max = 20, message = "用户名长度必须在4-20之间", groups = Register.class)
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "用户名必须是字母或者数字", groups = Register.class)
    private String username;

    /**
     * 头像;头像
     */
    private String avatar;

    /**
     * 密码;密码
     */
    @NotBlank(message = "密码不能为空", groups = {Register.class, Login.class})
    @Length(min = 3, max = 20, message = "密码长度必须在6-20之间", groups = Register.class)
    private String password;

    @NotBlank(message = "用户名不能为空", groups = {Register.class})
    private String nickName;
    /**
     * 邮箱;邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = {Register.class, EmailLogin.class})
    @Email(message = "邮箱格式不正确", groups = Register.class)
    private String email;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空", groups = Register.class)
    private String verifyCode;

    /**
     * 创建时间;创建时间
     */
    private Date[] createdTime;

    /**
     * 更新时间;最后更新时间
     */
    private Date updatedTime;

    /**
     * 状态;状态,1正常0禁用
     */
    private Integer status;

    /**
     * 逻辑删除
     */
    private Integer logicDelete;

    /**
     * 为0是账号登录
     * 为1 是邮箱登录
     */
    /*@NotBlank(message = "登录类型不能为空",groups =Login.class )
    private Integer type;*/
    private static final long serialVersionUID = 1L;
}