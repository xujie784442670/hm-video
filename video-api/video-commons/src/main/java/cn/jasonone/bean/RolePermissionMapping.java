package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限映射表
 * @TableName role_permission_mapping
 */
@Data
public class RolePermissionMapping implements Serializable {
    /**
     * 角色ID;角色ID
     */
    private Long roleId;

    /**
     * 权限ID;权限ID
     */
    private Long permissionId;

    private static final long serialVersionUID = 1L;
}