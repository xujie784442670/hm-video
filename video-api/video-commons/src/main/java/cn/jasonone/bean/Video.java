package cn.jasonone.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频表
 * @TableName video
 */
@Data
public class Video implements Serializable {
    /**
     * 视频id
     */
    private Long id;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频类型id
     */
    private Long typeId;
    /**
     * 视频类型
     */
    private String type;

    /**
     * 视频创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 视频封面地址
     */
    private String coverUrl;
    /**
     * 视频发布者id
     */
    private Long accountId;
    /**
     * 视频发布者昵称
     */
    private String nickName;
    /**
     * 视频发布者信息
     */
    private Account account;

    /**
     * 视频弹幕数量
     */
    private Integer barrages;
    /**
     * 视频收藏量
     */
    private Integer collections;
    /**
     * 视频状态:0-正常,1-禁用
     */
    private Integer status;
    /**
     * 视频评论数
     */
    private Integer comments;
    /**
     * 视频点赞数
     */
    private Integer likes;
    /**
     * 逻辑删除:0-未删除,1-已删除
     */
    private Integer logicalDelete;
    /**
     * 播放量
     */
    private Integer views;
    private static final long serialVersionUID = 1L;
}