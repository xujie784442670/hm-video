package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限
 * @TableName permission
 */
@Data
public class Permission implements Serializable {
    /**
     * 主键;主键
     */
    private Long id;

    /**
     * 父权限ID;父权限ID
     */
    private String pid;

    /**
     * 权限名称;权限名称
     */
    private String name;

    /**
     * 权限代码;权限代码
     */
    private String code;

    /**
     * 创建时间;创建时间
     */
    private Date createdTime;

    /**
     * 更新时间;最后更新时间
     */
    private Date updatedTime;

    /**
     * 状态;状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}