package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 关注表
 * @TableName focus
 */
@Data
public class Focus implements Serializable {
    /**
     * 关注者id
     */
    private Long accountId;

    /**
     * 被关注者id
     */
    private Long focusId;

    private static final long serialVersionUID = 1L;
}