package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 账号角色映射表
 * @TableName account_role_mapping
 */
@Data
public class AccountRoleMapping implements Serializable {
    /**
     * 账号ID;账号ID
     */
    private Long accountId;

    /**
     * 角色ID;角色ID
     */
    private Long roleId;

    private static final long serialVersionUID = 1L;
}