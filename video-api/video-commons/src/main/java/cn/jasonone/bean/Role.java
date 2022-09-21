package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 * @TableName role
 */
@Data
public class Role implements Serializable {
    /**
     * 主键;主键
     */
    private Long id;

    /**
     * 角色名称;角色名称
     */
    private String name;

    /**
     * 角色代码;角色代码
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