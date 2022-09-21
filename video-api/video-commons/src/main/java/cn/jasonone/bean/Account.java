package cn.jasonone.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表;用户表
 * @TableName account
 */
@Data
public class Account implements Serializable {
    /**
     * 主键;主键
     */
    private Long id;
    /**
     * 用户名;用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像;头像
     */
    private String avatar;
    /**
     * 密码;密码
     */
    private String password;
    /**
     * 邮箱;邮箱
     */
    private String email;
    /**
     * 创建时间;创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    /**
     * 更新时间;最后更新时间
     */
    private Date updatedTime;
    /**
     * 状态;状态,1正常0禁用
     */
    private Integer status;
    /**
     * 逻辑删除;0正常，1封禁
     */
    private Integer logicDelete;
    /**
     * 粉丝数
     */
    private Integer fans;
    /**
     * 关注数
     */
    private Integer focus;
    private static final long serialVersionUID = 1L;
}