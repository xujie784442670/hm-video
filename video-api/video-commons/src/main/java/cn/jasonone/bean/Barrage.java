package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 弹幕表
 * @TableName barrage
 */
@Data
public class Barrage implements Serializable {
    /**
     * 弹幕id
     */
    private Long id;

    /**
     * 视频id
     */
    private Long videoId;

    /**
     * 弹幕内容
     */
    private String content;

    /**
     * 弹幕出现时间
     */
    private Integer time;

    /**
     * 弹幕颜色
     */
    private String color;

    /**
     * 弹幕字体大小
     */
    private Integer fontSize;

    /**
     * 弹幕发送者id
     */
    private Long accountId;

    /**
     * 弹幕状态:0-正常,1-禁用
     */
    private Integer status;

    /**
     * 逻辑删除:0-未删除,1-已删除
     */
    private Integer logicalDelete;

    private static final long serialVersionUID = 1L;
}