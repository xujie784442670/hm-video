package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 广告表
 * @TableName advertise
 */
@Data
public class Advertise implements Serializable {
    /**
     * 广告id
     */
    private Long id;

    /**
     * 广告海报地址
     */
    private String posterUrl;

    /**
     * 广告外链
     */
    private String url;

    /**
     * 广告发送者id
     */
    private Long accountId;
    /**
     * 广告发送者
     */
    private String nickName;
    /**
     * 广告状态:0-正常,1-禁用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}