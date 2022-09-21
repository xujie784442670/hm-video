package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 点赞表
 * @TableName likes
 */
@Data
public class Likes implements Serializable {
    /**
     * 点赞id
     */
    private Long id;

    /**
     * 点赞视频id
     */
    private Long videoId;

    /**
     * 点赞评论id
     */
    private Long commentId;

    /**
     * 点赞类型 0:视频,1:评论
     */
    private Integer type;

    /**
     * 点赞用户
     */
    private Long accountId;

    private static final long serialVersionUID = 1L;
}