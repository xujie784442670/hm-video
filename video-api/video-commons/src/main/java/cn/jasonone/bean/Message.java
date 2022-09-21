package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息表
 * @TableName message
 */
@Data
public class Message implements Serializable {
    /**
     * 消息id
     */
    private Long id;

    /**
     * 消息发送者id
     */
    private Long accountId;

    /**
     * 消息接收者id
     */
    private Long acceptId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息发送时间
     */
    private Date createTime;

    /**
     * 逻辑删除:0-未删除,1-已删除
     */
    private Integer logicalDelete;

    private static final long serialVersionUID = 1L;
}